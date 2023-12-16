package controller;

import model.News;
import Service.LoadDataNewsToWeb;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class LoadDataNews extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<News> newsList = LoadDataNewsToWeb.getNewsData();
        request.setAttribute("newsList", newsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/UI.jsp"); // Thay đổi đường dẫn đến trang jsp của bạn
        dispatcher.forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
}
