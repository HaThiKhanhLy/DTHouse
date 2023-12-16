<%@ page import="Service.LoadDataNewsToWeb" %>
<$@ page import="java.util.List" $>
<%@ page import="model.News" %>
<%@ page import="controller.LoadDataNews" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Mockup UI</title>
    <style>
        @media only screen and (max-width: 900px) {
            .item {
                width: 48% !important;
            }
        }
        @media only screen and (max-width: 580px) {
            .item {
                width: 100% !important;
            }
        }
    </style>
</head>
<body>
<%
    List<News> newsList = LoadDataNewsToWeb.getNewsData();
%>
<div style="max-width: 1200px; margin: auto">

    <div>
        <table style="width: 100%">
            <tr>
                <td style="font-size: 36px; line-height: 40px; color: #154aad; font-weight: bolder; border-bottom: 1px solid #706f6f">
                    Báo mới
                </td>
            </tr>
        </table>
        <div style="color: #727171">
            <%=LocalDate.now()%>
        </div>
    </div>
    <%
        for (int i = 0; i < newsList.size(); i++) {
    %>
    <div style="display: flex; justify-content: space-between; flex-wrap: wrap">
        <div class="item" style="width: 30%; ">
            <div style="font-weight: bold; font-size: 20px; margin: 16px auto;">

            </div>
            <div>
            </div>
				<span style="padding-right: 12px">
					<img style="margin-bottom: -4px" width="20" height="20" src=<%=newsList.get(i).getImageUrl()%>/>
				</span>
                <span>
					<%=newsList.get(i).getDateTime()%>>
				</span>
                <span style="margin: 20px">|</span>
                <span style="padding-right: 12px">
					<svg style="margin-bottom: -4px" xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="20" height="20" viewBox="0 0 50 50">
						<path d="M 25 4.0625 C 12.414063 4.0625 2.0625 12.925781 2.0625 24 C 2.0625 30.425781 5.625 36.09375 11 39.71875 C 10.992188 39.933594 11 40.265625 10.71875 41.3125 C 10.371094 42.605469 9.683594 44.4375 8.25 46.46875 L 7.21875 47.90625 L 9 47.9375 C 15.175781 47.964844 18.753906 43.90625 19.3125 43.25 C 21.136719 43.65625 23.035156 43.9375 25 43.9375 C 37.582031 43.9375 47.9375 35.074219 47.9375 24 C 47.9375 12.925781 37.582031 4.0625 25 4.0625 Z M 25 5.9375 C 36.714844 5.9375 46.0625 14.089844 46.0625 24 C 46.0625 33.910156 36.714844 42.0625 25 42.0625 C 22.996094 42.0625 21.050781 41.820313 19.21875 41.375 L 18.65625 41.25 L 18.28125 41.71875 C 18.28125 41.71875 15.390625 44.976563 10.78125 45.75 C 11.613281 44.257813 12.246094 42.871094 12.53125 41.8125 C 12.929688 40.332031 12.9375 39.3125 12.9375 39.3125 L 12.9375 38.8125 L 12.5 38.53125 C 7.273438 35.21875 3.9375 29.941406 3.9375 24 C 3.9375 14.089844 13.28125 5.9375 25 5.9375 Z"></path>
					</svg>
				</span>
                <span>
					Event: <%=newsList.get(i).getTopic()%>
				</span>
            </div>
            <div style="padding-bottom: 20px; border-bottom: 1px solid #727171">
                <div style=" color: #154aad; font-size: 24px">
                    Tittle: <%=newsList.get(i).getTitle()%>
                </div>
                <div style="color: #727171">
                    <%=newsList.get(i).getContent()%>
                </div>
            </div>
            <div style="text-align: end; padding: 20px 0; color: #727171; font-size: 12px">
                <div>
                    Source: <%=newsList.get(i).getSource()%>
                </div>
                <div>
                    <a style="color: #706f6f" href="https://www.24h.com.vn/tin-tuc-trong-ngay/thu-tuong-chinh-phu-vuong-qua-nhieu-quy-dinh-thi-con-gi-la-co-che-dac-thu-c46a1521989.html">
                        <%=newsList.get(i).getLinkSource()%>
                    </a>
                </div>
            </div>
        </div>
    <%}%>
    </div>
</body>
</html>