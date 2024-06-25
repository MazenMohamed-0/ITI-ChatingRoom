package com.example.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    public static Connection connection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/itiproject", "root", "root");
            return connection;
        } catch (Exception e) {
            return null;
        }
    }

    public static void disConnect() throws SQLException {
        try {
            if (connection() != null && !connection().isClosed()) {
                connection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    ///////////////ADMIN/////////////////////
    public static void updateUser(String userID, String userName, String password) throws SQLException {
        Connection connection = connection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "update user_information set  user_name = ?, pass = ? where user_id = ?"
        );
        preparedStatement.setString(3, userID);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);

        preparedStatement.executeUpdate();

        disConnect();

    }

    public static void insertUser(String userName, String password) throws SQLException {
        Connection connection = connection();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into user_information (user_name, pass, statuse) values (?,?,?)"
        );

        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        preparedStatement.setBoolean(3, false);

        preparedStatement.executeUpdate();

        disConnect();

    }

    public static void deleteUser(String ID) throws SQLException {
        Connection connection = connection();

        PreparedStatement prep = connection.prepareStatement("delete from user_information where user_id = ?");
        prep.setString(1, ID);
        int rowsDeleted = prep.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("Row has been deleted successfully.");
        } else {
            System.out.println("No rows have been deleted.");
        }

        disConnect();
    }

    public static boolean Select_AdminLoginInfo(String userName, String password) throws SQLException {

        Connection connection = connection();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from admine where user_name = ? and pass = ? ");
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        ResultSet resultset = preparedStatement.executeQuery();
        if (resultset.next()) {
            return true;

        } else {
            return false;
        }

    }

    public static List<String> getOffline() throws SQLException {
        List<String> list = new ArrayList<>();
        Connection connection = connection();

        assert connection != null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select user_name from user_information where statuse = false");
        while (resultSet.next()) {
            String m = resultSet.getString("user_name");
            list.add(m);
            //System.out.println(resultSet.getString("user_name"));
        }
        disConnect();
        return list;
    }

    public static List<String> getOnline() throws SQLException {
        List<String> list = new ArrayList<>();
        Connection connection = connection();

        assert connection != null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select user_name from user_information where statuse = true");
        while (resultSet.next()) {
            String m = resultSet.getString("user_name");
            list.add(m);
            //System.out.println(resultSet.getString("user_name"));
        }
        disConnect();
        return list;
    }


    public static List<String> database() throws SQLException {
        List<String> list2 = new ArrayList<>();
        Connection connection = connection();

        assert connection != null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user_information");
        while (resultSet.next()) {
            String m = resultSet.getString("user_id");
            String m2 = resultSet.getString("user_name");
           String m3 = resultSet.getString("pass");
           String collect = ("\s\s\s\s"+ m + "\s\s\s\s\s\s\s\s\s\t\t" + m2 + "\s-\s" + m3);

            list2.add(collect);

        }
        disConnect();
        return list2;
    }


    //////////////////USER////////////////////////////
    public static boolean Select_loginInfo(String userName, String password) throws SQLException {

        Connection connection = connection();

        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from user_information where user_name = ? and pass = ? ");
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        ResultSet resultset = preparedStatement.executeQuery();
        if (resultset.next()) {
            return true;
        } else {
            return false;
        }

    }
    public static void insertAdmin(String AdminName, String password) throws SQLException {
        Connection connection = connection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into admine (user_name, pass) values (?,?)"
        );
        preparedStatement.setString(1, AdminName);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();
        disConnect();
    }

    public static void changeStatus(boolean status, String user_name) throws SQLException {
        Connection connection = connection();

        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(
                "update user_information set  statuse = ? where user_name = ?");
        preparedStatement.setBoolean(1, status);
        preparedStatement.setString(2, user_name);
        preparedStatement.executeUpdate();
    }



}
