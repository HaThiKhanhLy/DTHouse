package Service;

import controller.LoadDataNews;
import model.News;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LoadDataNewsToWeb extends LoadDataNews{
public static List<News> getNewsData() {
    //Connect database datamart with jdbcUrl = "jdbc:mysql://127.0.0.1:3306/datamart", jdbcUsername = "root", jdbcPassword ="123456"
    String datamartDbUrl = "jdbc:mysql://127.0.0.1:3306/datamart";
    String datamartUsername = "root";
    String datamartPassword = "123456";
    //Create a list containing a new list of posts
    List<News> newsList = new ArrayList<>();
    //Get data from news table
    String selectNewsQuery = "SELECT * FROM news";
    try (Connection datamartConnection = DriverManager.getConnection(datamartDbUrl, datamartUsername, datamartPassword);
         Statement datamartStatement = datamartConnection.createStatement();
         ResultSet datamartResultSet = datamartStatement.executeQuery(selectNewsQuery)) {
    //Insert data from news table to Arraylist newsList
        while (datamartResultSet.next()) {
            News news = new News(datamartResultSet.getInt(1),
                    datamartResultSet.getString(2),
                    datamartResultSet.getString(3),
                    datamartResultSet.getString(4),
                    datamartResultSet.getString(5),
                    datamartResultSet.getString(6),
                    datamartResultSet.getString(7),
                    datamartResultSet.getString(8),
                    datamartResultSet.getString(9));
            newsList.add(news);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return newsList;
}
}
