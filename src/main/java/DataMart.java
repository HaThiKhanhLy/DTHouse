import java.sql.*;

public class DataMart {
    public static void LoadDataMart() {

        String dataWarehouseDbUrl = "jdbc:mysql://127.0.0.1:3306/datawarehouse";
        String datawarehouseUsername = "root";
        String datawarehouselPassword = "123456";

        String dataMartDbUrl = "jdbc:mysql://127.0.0.1:3306/datamart";
        String dataMartUsername = "root";
        String datamartPassword = "123456";

        try {
            // connection to DW database
            Connection DWConnection = DriverManager.getConnection(dataWarehouseDbUrl, datawarehouseUsername, datawarehouselPassword);
            String selectQuery = "SELECT\n" +
                    "    n.ID AS newsId,\n" +
                    "    td.Name AS topic,\n" +
                    "    n.LinkImage AS imageUrl,\n" +
                    "    n.Date_news AS datePost,\n" +
                    "    n.Event AS event,\n" +
                    "    n.Title AS title,\n" +
                    "    n.Content AS content,\n" +
                    "    n.Source AS source,\n" +
                    "    n.LinkSource AS linkSource\n" +
                    "FROM\n" +
                    "    news n\n" +
                    "JOIN\n" +
                    "    date_dim dd ON n.Datetime_ID = dd.ID\n" +
                    "JOIN\n" +
                    "    NewsTopic nt ON n.ID = nt.NewID\n" +
                    "JOIN\n" +
                    "    TopicDetail td ON nt.Topic_ID = td.ID;\n";
            Statement DWStatement = DWConnection.createStatement();
            ResultSet DWResultSet = DWStatement.executeQuery(selectQuery);

            //connection to data mart
            Connection dataMartConnection = DriverManager.getConnection(dataMartDbUrl, dataMartUsername, datamartPassword);

            //Duyet qua rows trong DWResultSet
            while (DWResultSet.next()) {
                String newsId = DWResultSet.getString("newsId");
                System.out.println("newsId: " + newsId);
                //check newsId is exist in news table
                String checkExistNewsIdQuery = "SELECT newsId FROM news WHERE newsId = ?";

                try (PreparedStatement checkNewsStatement = dataMartConnection.prepareStatement(checkExistNewsIdQuery)) {
                    checkNewsStatement.setString(1, newsId);
                    ResultSet existingNewsIdResultSet = checkNewsStatement.executeQuery();

                    if (existingNewsIdResultSet.next()) {
                        // newsId đã tồn tại, update trong bảng news
                      String updateNewsQuery = "update news set topic = ?, imageUrl = ?, datePost = ?, event = ?, title = ?, content = ?, source = ?, linkSource = ? where newsId = ?";
                        PreparedStatement dataMartStatement = dataMartConnection.prepareStatement(updateNewsQuery);
                        dataMartStatement.setString(1,  DWResultSet.getString("topic"));
                        dataMartStatement.setString(2,  DWResultSet.getString("imageUrl"));
                        dataMartStatement.setString(3,  DWResultSet.getString("datePost"));
                        dataMartStatement.setString(4,  DWResultSet.getString("event"));
                        dataMartStatement.setString(5,  DWResultSet.getString("title"));
                        dataMartStatement.setString(6,  DWResultSet.getString("content"));
                        dataMartStatement.setString(7,  DWResultSet.getString("source"));
                        dataMartStatement.setString(8,  DWResultSet.getString("linkSource"));
                        dataMartStatement.setInt(9, existingNewsIdResultSet.getInt("newsId"));

                        dataMartStatement.executeUpdate();
                        System.out.println("news id is exist");
                    } else {
                        // newsId chưa tồn tại trong news table, insert vào bảng news
                        String insertNewsQuery = "insert into news (newsId, topic, imageUrl, datePost, event, title, content, source, linkSource) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        // String updateNewsQuery = "update news set newsId = ?, topic = ?, imageUrl = ?, datePost = ?, event = ?, title = ?, content = ?, source = ?, linkSource = ? where newsId = ?";
                        PreparedStatement dataMartStatement = dataMartConnection.prepareStatement(insertNewsQuery);
                        dataMartStatement.setInt(1, Integer.parseInt(DWResultSet.getString("newsId")));
                        dataMartStatement.setString(2,  DWResultSet.getString("topic"));
                        dataMartStatement.setString(3,  DWResultSet.getString("imageUrl"));
                        dataMartStatement.setString(4,  DWResultSet.getString("datePost"));
                        dataMartStatement.setString(5,  DWResultSet.getString("event"));
                        dataMartStatement.setString(6,  DWResultSet.getString("title"));
                        dataMartStatement.setString(7,  DWResultSet.getString("content"));
                        dataMartStatement.setString(8,  DWResultSet.getString("source"));
                        dataMartStatement.setString(9,  DWResultSet.getString("linkSource"));

                        dataMartStatement.executeUpdate();

                        System.out.println("news id is not exist");

                    }
                }

            }
            // close all connections

            DWConnection.close();
            dataMartConnection.close();
            System.out.println("Close connection success");

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
