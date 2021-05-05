import java.sql.*;
import java.lang.*;

public class TransactionsDistributedData5408 extends Thread {
    public void localOperations(Connection localhost) throws SQLException {
        String localQ1 = "UPDATE olist_customers_dataset SET customer_state = 'SK' WHERE customer_zip_code_prefix = 39400";
        String localQ2 = "UPDATE olist_customers_dataset SET customer_city = 'caxia do sul' WHERE customer_state = 'RJ'";
        String localQ3 = "UPDATE olist_geolocation_dataset SET geolocation_zip_code_prefix = 1036 WHERE geolocation_lng = -46.63427784";
        String localQ4 = "UPDATE olist_geolocation_dataset SET geolocation_city = 'St Pauls' WHERE geolocation_state = 'SP'";
        Statement T1 = localhost.createStatement();
        T1.executeUpdate(localQ1);
        T1.executeUpdate(localQ2);
        T1.executeUpdate(localQ3);
        T1.executeUpdate(localQ4);
    }
    public void remoteOperations(Connection remotehost) throws SQLException {
        String remoteQ1 = "INSERT INTO translations VALUES ('pet_shop', 'security_and_services')";
        String remoteQ2 = "UPDATE order_items SET price = 234.5 WHERE product_id = '4244733e06e7ecb4970a6e2683c13e61'";
        String remoteQ3 = "UPDATE sellers SET seller_state = 'RE' WHERE seller_city = 'brejao'";
        String remoteQ4 = "DELETE FROM products WHERE product_category_name = 'artes'";
        Statement T2 = remotehost.createStatement();
        T2.executeUpdate(remoteQ1);
        T2.executeUpdate(remoteQ2);
        T2.executeUpdate(remoteQ3);
        T2.executeUpdate(remoteQ4);


    }

    public void run() {
        String connectionUrlLocal = "jdbc:mysql://localhost:3306/userinfo";
        String localUser = "root";
        String localPassword = "007Helo@";

        String connectionUrlRemote = "jdbc:mysql://35.224.24.44:3306/purchaseInfo";
        String remoteUser = "root";
        String remotePassword = "007Helo@";

        Connection localhost = null;
        Connection remotehost = null; // remote connection from google Cloud Platform

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            localhost = DriverManager.getConnection(connectionUrlLocal, localUser, localPassword);
            remotehost = DriverManager.getConnection(connectionUrlRemote, remoteUser, remotePassword);

            localhost.setAutoCommit(false);
            remotehost.setAutoCommit(false);

            localOperations(localhost);

            remoteOperations(remotehost);

            localhost.commit();
            remotehost.commit();


        } catch (Exception exThread) {
            exThread.printStackTrace();
        } finally {
            try {
                localhost.close();
                remotehost.close();
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
    }

}

