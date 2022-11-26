package models;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.xpath.XPathExpressionException;

import models.config.Config;

public class ShopORM {
	static Connection connection;
    static PreparedStatement st;

    private ShopORM() {}

    public static Connection getConnection() throws SQLException, XPathExpressionException, FileNotFoundException, ClassNotFoundException {
        if (connection == null) {
        	Class.forName(Config.DRIVER);
    		var url = "jdbc:mysql://localhost/" + Config.DB;
    		return DriverManager.getConnection(url,Config.LOGIN,Config.PASS);
        }
        return connection;
    }

    public static ResultSet select(String sql) throws SQLException, XPathExpressionException, FileNotFoundException, ClassNotFoundException {
        st = getConnection().prepareStatement(sql);
        return st.executeQuery();
    }
    public static PreparedStatement getStatement(String sql) throws SQLException, XPathExpressionException, FileNotFoundException, ClassNotFoundException {
        st = getConnection().prepareStatement(sql);
        return st;
    }
    public static int execute(String sql) throws SQLException, XPathExpressionException, FileNotFoundException, ClassNotFoundException {
        st = getConnection().prepareStatement(sql);
        return st.executeUpdate();
    }

    public static void close() throws SQLException {
        st.close();
        connection.close();
    }
}
