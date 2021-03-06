USE [BookStore1]
GO
/****** Object:  Table [dbo].[bill]    Script Date: 6/14/2022 9:14:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[bill](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_name] [varchar](255) NULL,
	[created_date] [date] NULL,
	[total] [float] NULL,
	[discount_code] [varchar](255) NULL,
	[payment_method] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[bill_detail]    Script Date: 6/14/2022 9:14:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[bill_detail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[bill_id] [int] NULL,
	[book_id] [int] NULL,
	[price] [float] NULL,
	[sub_total] [float] NULL,
	[quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[book]    Script Date: 6/14/2022 9:14:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[book](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](255) NULL,
	[price] [float] NULL,
	[author] [nvarchar](255) NULL,
	[status] [int] NULL,
	[description] [nvarchar](max) NULL,
	[category_id] [int] NULL,
	[quantity] [int] NULL,
	[name] [nvarchar](255) NULL,
	[image] [varchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cart]    Script Date: 6/14/2022 9:14:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cart](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[book_id] [int] NULL,
	[quantity] [int] NULL,
	[user_name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category]    Script Date: 6/14/2022 9:14:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[discount]    Script Date: 6/14/2022 9:14:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[discount](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](255) NULL,
	[expire_date] [date] NULL,
	[status] [int] NULL,
	[_percent] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user]    Script Date: 6/14/2022 9:14:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_name] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[role] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[bill] ON 

INSERT [dbo].[bill] ([id], [user_name], [created_date], [total], [discount_code], [payment_method]) VALUES (14, N'user', CAST(N'2022-06-06' AS Date), 74750, N'GIAMGIA123', N'PAYPAL')
INSERT [dbo].[bill] ([id], [user_name], [created_date], [total], [discount_code], [payment_method]) VALUES (15, N'user', CAST(N'2022-06-06' AS Date), 149500, N'KTOOL', N'PHỔ THÔNG')
INSERT [dbo].[bill] ([id], [user_name], [created_date], [total], [discount_code], [payment_method]) VALUES (16, N'user', CAST(N'2022-06-06' AS Date), 11960, N'TEST3', N'PAYPAL')
INSERT [dbo].[bill] ([id], [user_name], [created_date], [total], [discount_code], [payment_method]) VALUES (17, N'user', CAST(N'2022-06-13' AS Date), 35680, N'TEST4', N'PAYPAL')
SET IDENTITY_INSERT [dbo].[bill] OFF
SET IDENTITY_INSERT [dbo].[bill_detail] ON 

INSERT [dbo].[bill_detail] ([id], [bill_id], [book_id], [price], [sub_total], [quantity]) VALUES (17, 14, 1, 149500, 149500, 1)
INSERT [dbo].[bill_detail] ([id], [bill_id], [book_id], [price], [sub_total], [quantity]) VALUES (18, 15, 1, 299000, 299000, 1)
INSERT [dbo].[bill_detail] ([id], [bill_id], [book_id], [price], [sub_total], [quantity]) VALUES (19, 16, 1, 29900, 119600, 4)
INSERT [dbo].[bill_detail] ([id], [bill_id], [book_id], [price], [sub_total], [quantity]) VALUES (20, 17, 9, 40000, 40000, 1)
INSERT [dbo].[bill_detail] ([id], [bill_id], [book_id], [price], [sub_total], [quantity]) VALUES (21, 17, 7, 49200, 49200, 1)
SET IDENTITY_INSERT [dbo].[bill_detail] OFF
SET IDENTITY_INSERT [dbo].[book] ON 

INSERT [dbo].[book] ([id], [title], [price], [author], [status], [description], [category_id], [quantity], [name], [image]) VALUES (1, N'Ngành IT', 299000, N'Thái Phạm', 0, N'THÁI PHẠM LÀ AI?
Anh Thái Phạm, Founder Cộng đồng Happy Live – Đầu tư tài chính và thịnh vượng, nguyên là Giám đốc Marketing của Vinamilk với 13 năm kinh nghiệm trong việc xây dựng thương hiệu, marketing và phát triển kinh doanh. Anh được mọi người biết đến như một nhà đầu tư, một doanh nhân đam mê giáo dục, huấn luyện và viết lách.

Anh cũng là biên dịch, dịch giả của khá nhiều cuốn sách về đầu tư & marketing bán chạy như: Ngày đòi nợ, Làm giàu từ chứng khoán, Nghệ thuật đầu tư Dhandho, Lột xác để trở thành nhà đầu tư giá trị, Marketing giỏi phải kiếm được tiền, Hệ thống bán hàng đỉnh cao, Nuốt cá lớn…

Ngoài ra, kênh youtube Thái Phạm với tinh thần: thay đổi cuộc sống bằng cách giúp mọi người trở nên giàu có hơn, cũng nhận được gần 200.000 lượt người đăng ký quan tâm và theo dõi.
THIẾT KẾ CUỘC ĐỜI THỊNH VƯỢNG – HƯỚNG DẪN CHI TIẾT CÁCH XÂY DỰNG CUỘC ĐỜI ĐÁNG MƠ ƯỚC CỦA BẠN

Có thể nói, đây vừa là một quyển tự truyện đi kèm với những bí quyết tạo nên một cuộc đời đáng sống trong suốt 20 năm “lăn lộn” trường đời của tác giả. 

Quyển sách không ru ngủ bạn với những công thức thay đổi bản thân thần thánh mà tập trung vào giải quyết những vấn đề của bạn bằng cách đưa ra các phương pháp đánh giá, nhìn nhận bản thân và cung cấp cho bạn những “vật tư” xây nên một cuộc đời bạn mong muốn. 

Bởi trước khi xây trên nền đất mới những công trình kiên cố, bạn phải đập bỏ những suy nghĩ cũ rích – những thứ vẫn đang giữ chân bạn với sự tầm thường. Bạn không thể xây nên một ngôi nhà đẹp và vững chắc từ một nền nhà kém chất lượng. Những viên gạch kém chất lượng mà bạn cần đập bỏ là những thành kiến về người giàu, những suy nghĩ sai lệch về thành công như chỉ có học đại học là con đường duy nhất để đạt được mục tiêu, ai thành công cũng đều hạnh phúc, theo đuổi đam mê, tiền sẽ tới,… Những điều nghe có vẻ hay, có vẻ ổn nhưng hệ quả của những suy nghĩ đó với cuộc sống của bạn không ổn chút nào. ', 1, 72, N'Thiết Kế Cuộc Đời Thịnh Vượng - Design a Prosperous Life', N'/images/books/20220529_060747.png')
INSERT [dbo].[book] ([id], [title], [price], [author], [status], [description], [category_id], [quantity], [name], [image]) VALUES (2, N'Ngành IT', 169000, N'Nhóm tác giả Spiderum', 0, N'"Học công nghệ thông tin rồi đi lắp máy tính với cài win dạo à?"
"Sao lại lao đầu vào cái nghề toàn số má với mấy dòng lệnh khó hiểu thế?"
"Dân làm máy tính vừa “đụt” vừa khô khan, ai yêu nổi, yêu nổi ai?"
"Lập trình khó lắm, chắc phải siêu sao toán mới học được."
"Làm công nghệ lương nghìn đô như bỡn!"
Đó chắc hẳn là những câu hội thoại, những lời đánh giá mà các bạn đang học/làm Công nghệ thông tin (CNTT) gặp phải ít nhất 1 lần trong đời, còn những người không theo học/làm CNTT từng nghĩ đến ít nhất 1 lần trong đời (trung thực với bản thân và thừa nhận đi!).

Trong quá trình thực hiện bộ sách hướng nghiệp cho các bạn trẻ, chúng tôi cũng không ngừng trăn trở: Ngành CNTT có thật sự như vậy không? Làm sao để giúp các bạn trẻ có được một cái nhìn toàn diện và khách quan nhất về lĩnh vực rộng lớn này? Những cuốn sách về CNTT hiện có trên thị trường thường là tài liệu nặng tính kỹ thuật dành cho dân chuyên ngành, hoặc những bài viết ngắn với thông tin khá chung chung, hoặc chỉ có góc nhìn từ một cá nhân nên thiếu tính đa chiều. Vậy là nhóm độc giả nằm “lưng chừng” như những bạn học sinh cấp 3, sinh viên Đại học và các bậc phụ huynh đang quan tâm, muốn tìm hiểu về ngành CNTT bị bỏ mặc “bơ vơ”, biết đọc gì đây?

Đó là lý do cuốn sách bạn đang cầm trên tay ra đời.

 

Nằm trong series sách hướng nghiệp của Spiderum và TopCV, “Người trong muôn nghề: Ngành IT có gì?” là:

Cuốn sách đầu tiên trên thị trường đem đến bức tranh toàn cảnh về ngành CNTT cũng như lộ trình phát triển của các vị trí nghề nghiệp phổ biến trong lĩnh vực này. 

Cuốn sách đầu tiên giúp bạn hiểu ngành CNTT không chỉ dừng ở chiếc máy tính hay những dòng code khô khan mà còn rất nhiều những câu chuyện thú vị khác: thiết kế, làm sản phẩm, làm game, làm ứng dụng di động, làm web, gặp gỡ và giao tiếp khách hàng,

Cuốn sách đầu tiên “phá vỡ” các định kiến như: Làm CNTT chỉ toàn những kẻ nhút nhát, “đầu to mắt cận” hay Nghề này không dành cho con gái. Bạn sẽ thấy, dân IT cũng bạo dạn đi Tây đi Tàu “chinh chiến” thật oách, thấy ngành này chẳng hề khô khan chút nào qua góc nhìn của một người vợ có chồng là lập trình viên, và thấy các bạn nữ cũng không thua kém cánh đàn ông về khoản công nghệ nếu thật sự yêu thích.

Cuốn sách là tập hợp 21 bài viết chứa đựng những chia sẻ “móc hết gan ruột” của các tác giả - những người trực tiếp hoạt động trong lĩnh vực công nghệ. Họ ở đủ mọi độ tuổi, vị trí công việc, địa lý, giới tính: Từ bạn trẻ đang là sinh viên, những người mới vào nghề vài năm tới các đàn anh cỡ 20 năm trong nghề; Từ người học tập và sinh sống tại nước ngoài cho đến bạn xuất thân bình dị từ làng quê nghèo; Từ bậc tiền bối đặt nền móng cho nền CNTT Việt Nam trong lịch sử tới các doanh nhân thời hiện đại gây dựng những công ty công nghệ trị giá hàng triệu, hàng tỷ USD,

 

Nội dung sách chia làm 3 chương:

Chương 1: Tổng quan ngành IT

Cung cấp thông tin cơ bản về các vị trí nghề nghiệp, mức thu nhập trung bình và những môi trường làm việc đặc thù trong ngành. Ngoài ra, bạn còn được đến với những câu chuyện về lịch sử phát triển của CNTT tại Việt Nam, cũng như nắm bắt các diễn biến trong hiện tại và xu hướng tương lai qua góc nhìn của các Tech Founder. 

Chương 2: Muôn nẻo đường nghề

Đi sâu vào từng vị trí việc làm cụ thể thông qua trải nghiệm thực tế của những người trong ngành, như: Đặc điểm, vai trò của từng loại công việc; Các kiến thức, kỹ năng các bạn cần chuẩn bị và những bài học, kỷ niệm vui, buồn trong nghề.

Chương 3: Hành trang vào ngành

Những “trang bị" cần thiết cả về mặt thái độ, tâm lý lẫn kiến thức để bạn có thể phát huy tối đa khả năng trong hành trình chinh phục thế giới công nghệ.

Là sản phẩm của hơn 4 tháng lao động miệt mài, chúng tôi tin cuốn sách sẽ giúp bạn gạt bớt những hoang mang, rối ren khi chọn ngành, chọn nghề thông qua việc nắm bắt tổng quan về lĩnh vực IT cũng như thấu hiểu chính bản thân.

Nếu bạn đang vướng mắc với câu hỏi: “Liệu mình có nên chọn ngành CNTT?”, đây là cuốn sách dành cho bạn.

Đừng chần chừ, hãy đến với những trang sách tiếp theo để khám phá thế giới công nghệ đầy màu sắc. Một hành trình tuyệt vời đang chờ bạn phía trước!

Giá sản phẩm trên Tiki đã bao gồm thuế theo luật hiện hành. Bên cạnh đó, tuỳ vào loại sản phẩm, hình thức và địa chỉ giao hàng mà có thể phát sinh thêm chi phí khác như phí vận chuyển, phụ phí hàng cồng kềnh, thuế nhập khẩu (đối với đơn hàng giao từ nước ngoài có giá trị trên 1 triệu đồng).....', 1, 93, N'Sách Người Trong Muôn Nghề: Ngành IT có gì?', N'/images/books/20220529_061735.jpg')
INSERT [dbo].[book] ([id], [title], [price], [author], [status], [description], [category_id], [quantity], [name], [image]) VALUES (3, N'Ngành IT', 68250, N'Anonymous', 0, N'Cẩm nang hướng dẫn hoàn chỉnh và đơn giản nhất dành cho bạn trẻ bắt đầu học lập trình
Ngôn ngữ lập trình Scratch đặc biệt phù hợp cho bạn trẻ mới học, vì tính tương tác trực quan, đồ họa sống động, ra sản phẩm liền tay mà vẫn đảm bảo khoa học và liên thông tri thức sau này.

Chỉ cần nắm và kéo các khối lệnh đầy màu sắc có sẵn để lắp ghép thành một kịch bản điều khiển các đối tượng trên màn hình.

Không có những dòng lệnh logic khô cứng và dễ lỗi, những khái niệm kỹ thuật khó hiểu, những quy tắc luật lệ chằng chịt và mệt mỏi trong các ngôn ngữ lập trình kiểu người lớn.

Giá sản phẩm trên Tiki đã bao gồm thuế theo luật hiện hành. Bên cạnh đó, tuỳ vào loại sản phẩm, hình thức và địa chỉ giao hàng mà có thể phát sinh thêm chi phí khác như phí vận chuyển, phụ phí hàng cồng kềnh, thuế nhập khẩu (đối với đơn hàng giao từ nước ngoài có giá trị trên 1 triệu đồng).....', 1, 998, N'Tớ Học Lập Trình - Làm Quen Với Lập Trình Scratch', N'/images/books/20220529_062007.jpg')
INSERT [dbo].[book] ([id], [title], [price], [author], [status], [description], [category_id], [quantity], [name], [image]) VALUES (4, N'Lập Trình Hướng Đối Tượng', 249000, N'No Name', 0, N'I. Đôi điều về tác giả

Tôi là NEOS.THÀNH (Nguyễn Văn Thành) – Một lập trình viên Java-Android, tác giả cuốn sách “Lập trình hướng đối tượng Java Core”, CEO của công ty TNHH MTV DV Giáo Dục Thành Nguyên, đồng thời là mentor tại trường ĐH trực tuyến FUNiX, giảng viên giảng dạy tại cao đẳng nghề PolyTechnic,  công ty phần mềm Luvina và công ty phần mềm FPT.
II. Quyển sách này nói về điều gì?

JAVA là ngôn ngữ lập trình rất phổ biến nhất hiện nay, học #JAVA_CORE bạn sẽ có rất nhiều hướng đi, từ lập trình Mobile app, Java web, Desktop App, Game, và tất cả đều sử dụng nền tảng của JAVA CORE.
Quyển sách này gồm 22 bài học từ Tư duy LTHĐT(Đa hình, kế thừ) đến các đối tượng #JavaCore (String, Array, File), lập trình giao diện Swing.
Quyển sách này sẽ giúp bạn:

Đi vào thế giới lập trình hết sức tự nhiên, thân thiện và dễ hiểu - LẬP TRÌNH HƯỚNG ĐỐI TƯỢNG LÀ TƯ DUY GẮN LIỀN VỚI CUỘC SỐNG HẰNG NGÀY
Nắm vững được thế nào là tư duy lập trình hướng đối tượng và cách phân tích một bài toán lập trình
Hiểu được các khái niệm lập trình Java cơ bản.
Thực hành xây dựng được các giao diện phần mềm desktop bằng ngôn ngữ JAVA
Sau khi có được nền tảng kiến thức lập trình JAVA core bạn có thể tự học các ngôn ngữ lập trình hướng đối tượng khác như C++/C, Python,
III. Quyển sách này dành cho ai?

Là sách tham khảo, hướng dẫn tự học lập trình hướng đối tượng bằng ngôn ngữ JAVACore
Dành cho người mới bắt đầu học lập trình, sinh viên chưa vững tư duy LTHĐT, Java core
Dành cho người mất gốc hoặc trái ngành muốn học lập trình
#SACHLAPTRINH #LậpTrìnhHướngĐốiTượngJAVA #DạyLậpTrình #LậpTrìnhJavaCănBản

Giá sản phẩm trên Tiki đã bao gồm thuế theo luật hiện hành. Bên cạnh đó, tuỳ vào loại sản phẩm, hình thức và địa chỉ giao hàng mà có thể phát sinh thêm chi phí khác như phí vận chuyển, phụ phí hàng cồng kềnh, thuế nhập khẩu (đối với đơn hàng giao từ nước ngoài có giá trị trên 1 triệu đồng).....', 1, 99, N'Lập trình hướng đối tượng JAVA core dành cho người mới bắt đầu học lập trình', N'/images/books/20220529_062210.jpg')
INSERT [dbo].[book] ([id], [title], [price], [author], [status], [description], [category_id], [quantity], [name], [image]) VALUES (5, N'Lập Trình Python', 125000, N'Bùi Việt Hà', 0, N'SÁCH PYTHON CƠ BẢN

Hiện nay ngôn ngữ lập trình bậc cao Python đang nổi lên như một ngôn ngữ lập trình được sử dụng NHIỀU NHẤT trên thế giới. Điều này được giải thích bằng các lý do sau:

Python là ngôn ngữ lập trình bậc cao khá đơn giản, dễ học, dễ viết.

Cách viết lệnh của Python khá đặc biệt, sử dụng các dấu cách (viết thụt vào) để mô tả các nhóm (block) lệnh. Đặc điểm này làm cho việc viết lệnh Python gần giống với cách viết, trình bày văn bản hàng ngày. Chính đặc điểm này làm cho ngôn ngữ lập trình Python rất dễ viết, trong sáng, ngày càng phát triển và được đưa vào môi trường giáo dục thay cho các ngôn ngữ truyền thống như Pascal, C hay Java.

Python là ngôn ngữ mã nguồn mở và cho phép cộng đồng có thể đóng góp bằng cách bổ sung các module, các kho hàm số, thư viện thuật toán. Điều này làm cho Python phát triển BÙNG NỔ trong giới khoa học và giáo dục đại học. Đặc biệt trong một số ngành mũi nhọn của CNTT như IoT, trí tuệ nhân tạo (AI), dữ liệu lớn (big data) và CMCN 4.0, các phát triển rất nhanh thời gian gần đây của công nghệ đều gắn liền với Python.

Ngoài các lý do nêu trên, Python còn có một tính chất khác biệt nữa: Python là ngôn ngữ thông dịch và luôn có môi trường tương tác Python Shell đi kèm. Chính môi trường tương tác này sẽ giúp ích rất nhiều cho những người muốn làm quen và học Python.

Sách Pyhon cơ bản là cuốn sách đầu tiên, cơ bản, dành cho người mới bắt đầu học ngôn ngữ lập trình này.

Sách dày 254 trang, bao gồm 16 chương, cùng với trên 350 bài tập từ đơn giản đến phức tạp, phù hợp cho mọi đối tượng từ cấp THCS, THPT hoặc sinh viên đại học. Sách cũng có thể dùng cho giáo viên dạy Tin học các trường phổ thông và đại học.


Nội dung các chủ đề của sách Python cơ bản như sau:
1. Bắt đầu với Python.
2. Làm quen môi trường lập trình Python.
3. Input và chuyển đổi dữ liệu.
4. Hàm số.
5. Đối tượng trong Python.
6. Kiểu dữ liệu List. Mảng một chiều.
7. List của List. Mảng nhiều chiều.
8. Khái niệm Module.
9. Xâu ký tự.
10. Đọc và ghi tệp.
11. Câu lệnh điều kiện.
12. Đệ quy.
13. Kiểu dữ liệu Từ điển.
14. Kiểu dữ liệu Tập hợp.
15. Đồ họa con Rùa.
16. Bắt lỗi và kiểm soát lỗi trong Python.
Mỗi chương sẽ bắt đầu bằng mô tả mục đích của chương, tiếp theo là dãy các hoạt động kiến thức cần học và dạy. Sách có thể dùng cho việc tự học hoặc giáo viên giảng dạy trên lớp. Sau mỗi chương là phần câu hỏi, bài tập chi tiết.

Giá sản phẩm trên Tiki đã bao gồm thuế theo luật hiện hành. Bên cạnh đó, tuỳ vào loại sản phẩm, hình thức và địa chỉ giao hàng mà có thể phát sinh thêm chi phí khác như phí vận chuyển, phụ phí hàng cồng kềnh, thuế nhập khẩu (đối với đơn hàng giao từ nước ngoài có giá trị trên 1 triệu đồng).....', 1, 98, N'Python cơ bản', N'/images/books/20220529_062631.jpg')
INSERT [dbo].[book] ([id], [title], [price], [author], [status], [description], [category_id], [quantity], [name], [image]) VALUES (6, N'Kiến Thức Code', 460500, N'Tôi đi code dạo', 0, N'Combo 2Q: Clean code – Mã Sạch Và Con Đường Trở Thành Lập Trình Viên Giỏi + Hello Các Bạn Mình Là Tôi Đi Code Dạo

1.Clean code – Mã Sạch Và Con Đường Trở Thành Lập Trình Viên Giỏi

Hiện nay, lập trình viên là một trong những nghề nghiệp được nhiều người lựa chọn theo đuổi và gắn bó. Đây là công việc đòi hỏi sự tỉ mỉ, nhiều công sức nhưng mang lại giá trị cao và một tương lai công việc hứa hẹn. Là một trong số những chuyên gia giàu kinh nghiệm, điêu luyện và lành nghề trong ngành, tác giả đã đúc rút từ kinh nghiệm của mình, viết về những tình huống trong thực tế, đôi khi có thể trái ngược với lý thuyết để xây dựng nên cuốn cẩm nang này, nhằm hỗ trợ những người đang muốn trở thành một lập trình viên chuyên nghiệp.

Cuốn sách được nhiều lập trình viên đánh giá là quyển sách giá trị nhất mà họ từng đọc trong sự nghiệp của mình. Cuốn sách hướng dẫn cách để viết những đoạn mã có thể hoạt động tốt, cũng như truyền tải được ý định của người viết nên chúng.

Cuốn sách được chia thành ba phần lớn. Phần đầu tiên mô tả các nguyên tắc, mô hình và cách thực hành viết mã sạch. Phần thứ hai gồm nhiều tình huống điển hình với mức độ phức tạp gia tang không ngừng. Mỗi tình huống là một bài tập giúp làm sạch mã, chuyển đổi mã có nhiều vấn đề thành mã có ít vấn đề hơn. Và phần cuối là tuyển tập rất nhiều những dấu hiệu của mã có vấn đề, những tìm tòi, suy nghiệm từ thực tiễn được đúc rút qua cách tình huống điển hình.

2.Hello Các Bạn Mình Là Tôi Đi Code Dạo

Cho tới thời điểm hiện tại, IT vẫn đang là một ngành hot và là sự lựa chọn của rất nhiều các bạn học sinh, sinh viên. Tuy nhiên, cho đến nay, những nguồn tài nguyên học tập trong ngành còn quá ít. Ngoài những tài liệu học tập của trường học thì những tài liệu khác chủ yếu vẫn là các thông tin trên internet hoặc từ những kinh nghiệm của những người đi trước. Để bạn đọc có cái nhìn chân thực và rõ nét hơn về ngành IT, thông qua cuốn sách này, tác giả Phạm Huy Hoàng đã tóm tắt và chia sẻ lại những câu chuyện, bài học kinh nghiệm đã gặp, đã đúc rút được để bạn đọc tham khảo và tìm ra những điểm tương đồng với những gì mình gặp trong khi học tập và làm việc để từ đó rút ra được bài học cho riêng mình.

“Tôi Đi Code Dạo” là một blog, fanpage và Youtube channel được sáng lập bởi Phạm Huy Hoàng - một full-stack developer đã tốt nghiệp Thạc sĩ ngành Computer Science tại Đại học Lancaster ở Anh (Học bổng 18.000 bảng)

và hiện đang làm việc cho một startup công nghệ tại Singapore.

Nội dung mà "Tôi Đi Code Dạo" chia sẻ một nửa là về kĩ thuật lập trình, một nửa còn lại là những kinh nghiệm mà "Mr.Code Dạo" đã học được như: cách deal lương, sắp xếp thời gian, kĩ năng mềm, ngôn ngữ lập trình nên học, con đường phát triển nghề nghiệp… Đó là những điều quan trọng không thua gì kĩ năng lập trình.

 

Giá sản phẩm trên Tiki đã bao gồm thuế theo luật hiện hành. Bên cạnh đó, tuỳ vào loại sản phẩm, hình thức và địa chỉ giao hàng mà có thể phát sinh thêm chi phí khác như phí vận chuyển, phụ phí hàng cồng kềnh, thuế nhập khẩu (đối với đơn hàng giao từ nước ngoài có giá trị trên 1 triệu đồng).....', 1, 97, N'Combo 2Q: Clean code – Mã Sạch Và Con Đường Trở Thành Lập Trình Viên Giỏi + Hello Các Bạn Mình Là Tôi Đi Code Dạo', N'/images/books/20220529_062820.jpg')
INSERT [dbo].[book] ([id], [title], [price], [author], [status], [description], [category_id], [quantity], [name], [image]) VALUES (7, N'Test', 123000, N'QuangKTool', 0, N'test_1', 1, 93, N'Test_1', N'/images/books/20220601_022652.jpg')
INSERT [dbo].[book] ([id], [title], [price], [author], [status], [description], [category_id], [quantity], [name], [image]) VALUES (8, N'Sach_test', 231000, N'QuangKTool', 0, N'Test lan 2
', 3, 95, N'Test_sach', N'/images/books/20220606_092934.jpg')
INSERT [dbo].[book] ([id], [title], [price], [author], [status], [description], [category_id], [quantity], [name], [image]) VALUES (9, N'Test 3', 100000, N'Linh Hải Nghiêm', 0, N'Test lan 3', 2, 99, N'Test lan 3', N'/images/books/20220613_125921.jpg')
SET IDENTITY_INSERT [dbo].[book] OFF
SET IDENTITY_INSERT [dbo].[category] ON 

INSERT [dbo].[category] ([id], [name]) VALUES (1, N'Lập trình')
INSERT [dbo].[category] ([id], [name]) VALUES (2, N'Tình cảm')
INSERT [dbo].[category] ([id], [name]) VALUES (3, N'Ngôn tình')
SET IDENTITY_INSERT [dbo].[category] OFF
SET IDENTITY_INSERT [dbo].[discount] ON 

INSERT [dbo].[discount] ([id], [code], [expire_date], [status], [_percent]) VALUES (1, N'TEST', CAST(N'2022-07-06' AS Date), 0, 20)
INSERT [dbo].[discount] ([id], [code], [expire_date], [status], [_percent]) VALUES (2, N'Y6RYMFUV', CAST(N'2022-06-07' AS Date), 0, 20)
INSERT [dbo].[discount] ([id], [code], [expire_date], [status], [_percent]) VALUES (3, N'KTOOL', CAST(N'2022-06-07' AS Date), 1, 50)
INSERT [dbo].[discount] ([id], [code], [expire_date], [status], [_percent]) VALUES (4, N'GIAMGIA123', CAST(N'2022-06-09' AS Date), 1, 50)
INSERT [dbo].[discount] ([id], [code], [expire_date], [status], [_percent]) VALUES (5, N'TEST3', CAST(N'2022-06-10' AS Date), 1, 90)
INSERT [dbo].[discount] ([id], [code], [expire_date], [status], [_percent]) VALUES (6, N'TEST4', CAST(N'2022-06-14' AS Date), 1, 60)
SET IDENTITY_INSERT [dbo].[discount] OFF
SET IDENTITY_INSERT [dbo].[user] ON 

INSERT [dbo].[user] ([id], [user_name], [password], [role]) VALUES (1, N'user', N'123', 1)
INSERT [dbo].[user] ([id], [user_name], [password], [role]) VALUES (2, N'admin', N'123', 0)
SET IDENTITY_INSERT [dbo].[user] OFF
SET ANSI_PADDING ON
GO
/****** Object:  Index [code]    Script Date: 6/14/2022 9:14:45 AM ******/
ALTER TABLE [dbo].[discount] ADD  CONSTRAINT [code] UNIQUE NONCLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [user_name]    Script Date: 6/14/2022 9:14:45 AM ******/
ALTER TABLE [dbo].[user] ADD  CONSTRAINT [user_name] UNIQUE NONCLUSTERED 
(
	[user_name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[bill]  WITH CHECK ADD  CONSTRAINT [bill_discount_code_fk] FOREIGN KEY([discount_code])
REFERENCES [dbo].[discount] ([code])
GO
ALTER TABLE [dbo].[bill] CHECK CONSTRAINT [bill_discount_code_fk]
GO
ALTER TABLE [dbo].[bill]  WITH CHECK ADD  CONSTRAINT [bill_user_user_name_fk] FOREIGN KEY([user_name])
REFERENCES [dbo].[user] ([user_name])
GO
ALTER TABLE [dbo].[bill] CHECK CONSTRAINT [bill_user_user_name_fk]
GO
ALTER TABLE [dbo].[bill_detail]  WITH CHECK ADD  CONSTRAINT [bill_detail_bill_id_fk] FOREIGN KEY([bill_id])
REFERENCES [dbo].[bill] ([id])
GO
ALTER TABLE [dbo].[bill_detail] CHECK CONSTRAINT [bill_detail_bill_id_fk]
GO
ALTER TABLE [dbo].[bill_detail]  WITH CHECK ADD  CONSTRAINT [bill_detail_book_id_fk] FOREIGN KEY([book_id])
REFERENCES [dbo].[book] ([id])
GO
ALTER TABLE [dbo].[bill_detail] CHECK CONSTRAINT [bill_detail_book_id_fk]
GO
ALTER TABLE [dbo].[book]  WITH CHECK ADD  CONSTRAINT [book_category_id_fk] FOREIGN KEY([category_id])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[book] CHECK CONSTRAINT [book_category_id_fk]
GO
ALTER TABLE [dbo].[cart]  WITH CHECK ADD  CONSTRAINT [cart_book_id_fk] FOREIGN KEY([book_id])
REFERENCES [dbo].[book] ([id])
GO
ALTER TABLE [dbo].[cart] CHECK CONSTRAINT [cart_book_id_fk]
GO
ALTER TABLE [dbo].[cart]  WITH CHECK ADD  CONSTRAINT [cart_user_user_name_fk] FOREIGN KEY([user_name])
REFERENCES [dbo].[user] ([user_name])
GO
ALTER TABLE [dbo].[cart] CHECK CONSTRAINT [cart_user_user_name_fk]
GO
