package com.udemy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Guillaume COLLET
 */
public class JdbcTest {
    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        /**
         * In case of WARN: Establishing SSL connection without server’s identity verification is not recommended"
         * message, use this URL:
         *
         * String dbUrl = "jdbc:mysql://localhost:3306/udemy_demo_jdbc/?useSSL=false";
         * */
        String dbUrl = "jdbc:mysql://localhost:3306/udemy_demo_jdbc";
        String user = "student";
        String pw = "student";

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection(dbUrl, user, pw);
            System.out.println("Database connection successful!\n");

            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myRs = myStmt.executeQuery("select * from employees");

            // 4. Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
    }
}
