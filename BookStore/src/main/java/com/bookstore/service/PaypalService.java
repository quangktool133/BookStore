package com.bookstore.service;

import com.bookstore.constant.BillEndpointConstant;
import com.bookstore.dto.CartDTO;
import com.bookstore.dto.ResultDTO;
import com.bookstore.entity.BillDetailPaypal;
import com.bookstore.entity.Discount;
import com.bookstore.entity.User;
import com.bookstore.util.ConfigurationUtil;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaypalService {


    private static final String CLIENT_ID = ConfigurationUtil.getValueConfig("paypal.client-id");
    private static final String CLIENT_SECRET = ConfigurationUtil.getValueConfig("paypal.secret-code");
    private static final String MODE = "sandbox";

    private DiscountService discountService;

    public PaypalService() {
        this.discountService = new DiscountService();
    }

    public ResultDTO<String> checkout(List<CartDTO> carts, User user, String discountCode) {

        try {

            if (Objects.isNull(carts) || carts.isEmpty()) {
                return new ResultDTO(null, true, "Chưa có sản phẩm trong giỏ hàng!");
            }

            Discount discount = null;

            if (Objects.nonNull(discountCode) || !discountCode.isEmpty()) {
                discount = this.discountService.findByCode(discountCode);
            }

            Payer payer = getPayerInformation(user);
            RedirectUrls redirectUrls = getRedirectURLs();
            List<Transaction> listTransaction = getTransactionInformation(carts, discount);

            Payment requestPayment = new Payment();
            requestPayment.setTransactions(listTransaction);
            requestPayment.setRedirectUrls(redirectUrls);
            requestPayment.setPayer(payer);
            requestPayment.setIntent("authorize");

            APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

            Payment approvedPayment = requestPayment.create(apiContext);
            String approvalLink = getApprovalLink(approvedPayment);
            return new ResultDTO<>(approvalLink, false, "");
        } catch (Exception exception) {
            return new ResultDTO<>(null, true, "Thanh toán thất bại với paypal");
        }
    }

    private Payer getPayerInformation(User user) {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName(user.getUsername());
        payer.setPayerInfo(payerInfo);
        return payer;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:6969/book-store/cart");
        redirectUrls.setReturnUrl("http://localhost:6969/book-store"+ BillEndpointConstant.BILL_GET_ITEM_PAYPAL);
        return redirectUrls;
    }

    private List<Transaction> getTransactionInformation(List<CartDTO> cartDTOList, Discount discount) {

        List<Transaction> listTransaction = new ArrayList<>();

        Float subtotal = cartDTOList.stream().map(CartDTO::getSubtotal).reduce(0.0F, (total, current) -> total + current);

        if (Objects.nonNull(discount)) {
            subtotal = subtotal - (subtotal * discount.getPercent() / 100);
        }

        Details details = new Details();
        details.setShipping("0");
        details.setSubtotal(subtotal+"");
        details.setTax("0");

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(subtotal+"");
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(Objects.nonNull(discount) ? discount.getCode() : null);

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();

        for (CartDTO cartDTO : cartDTOList) {

            if (Objects.nonNull(discount)) {
                cartDTO.getBook().setPrice( cartDTO.getBook().getPrice() - (cartDTO.getBook().getPrice() * discount.getPercent() / 100) );
            }

            Item item = new Item();
            item.setDescription(cartDTO.getId()+","+cartDTO.getBook().getId());
            item.setCurrency("USD");
            item.setName(cartDTO.getBook().getName());
            item.setPrice(cartDTO.getBook().getPrice()+"");
            item.setTax("0");
            item.setQuantity(cartDTO.getQuantity()+"");
            items.add(item);
        }

        itemList.setItems(items);
        transaction.setItemList(itemList);
        listTransaction.add(transaction);

        return listTransaction;
    }

    private String getApprovalLink(Payment approvedPayment) {
        return approvedPayment.getLinks().stream()
                .filter(link -> link.getRel().equalsIgnoreCase("approval_url"))
                .map(Links::getHref)
                .findFirst().orElseThrow(() -> new RuntimeException("not found link"));
    }


    public ResultDTO<Payment> getPaymentDetails(String paymentId) {
        try {
            APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
            return new ResultDTO<>(Payment.get(apiContext, paymentId), false, "");
        } catch(Exception ex) {
            return new ResultDTO<>(null, true, "Thanh toán thất bại paypal");
        }
    }
}