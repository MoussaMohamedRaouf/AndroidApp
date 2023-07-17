package functions.sql;

import exceptions.IncorrectSQLRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBInvoices {
    private static Connection conn;
    private static final Logger logger = LogManager.getLogger(DBInvoices.class);

    /**
     * Make the constructor private to not be able to instantiate it
     */
    private DBInvoices(){
    }

    /**
     * Generate A new connection if the current one is null on the first call.
     */
    private static void getConnection(){
        if(conn==null){
            logger.info("****** Trying to connect to Invoices DB");
            conn = DataBaseManager.connectDB("invoices");
            logger.info("****** Invoices DB connected");
        }
    }

    /**
     * Call closeDB method to close the bdd the to conn parameter
     */
    public static void closeDBInvoices(){
        if(conn!=null){
            logger.info("****** Trying to close Invoices DB");
            conn = DataBaseManager.closeDB(conn);
            logger.info("****** Invoices DB closed");
        }
    }

    /**
     * Using the 3 methods of deletion, this method will clean up the DB invoices from the us
     */
    public static void deleteUserFromInvoices(String userEmail){
        try {
            deleteUsersSaleJournals(userEmail);
            deleteUsersSales(userEmail);
            deleteUsersTransactions(userEmail);
        }catch(Exception e){
            logger.error(() -> "Something went wrong while deleting user's sales and transactions", e);
            e.printStackTrace();
            throw new IncorrectSQLRequestException("Something went wrong while deleting user's sales and transactions");
        }
    }

    /**
     * Thanks to this method, all the sales of the user will be deleted.
     */
    public static void deleteUsersSales(String userEmail){
        getConnection();
        PreparedStatement prepare = null;
        try{
            prepare = conn.prepareStatement("DELETE FROM sale WHERE account_email= ?");
            prepare.setString(1, userEmail);
            PreparedStatement finalPrepare = prepare;
            logger.info(() -> "****** Trying to execute SQL request to delete user from Sales :: " + finalPrepare.toString());
            prepare.executeUpdate();
        }catch (Exception e) {
            logger.error(() -> e);
            e.printStackTrace();
        }finally{
            try {
                assert prepare != null;
                prepare.close();
            } catch (SQLException throwable) {
                logger.error(() -> throwable);
                throwable.printStackTrace();
            }
        }
    }

    /**
     * Thanks to this method, all the transactions of the user will be deleted.
     */
    public static void deleteUsersTransactions(String userEmail){
        getConnection();
        PreparedStatement prepare = null;
        try{
            prepare = conn.prepareStatement("DELETE FROM transaction WHERE account_email= ?");
            prepare.setString(1, userEmail);
            PreparedStatement finalPrepare = prepare;
            logger.info(() -> "****** Trying to execute SQL request to delete user from Transactions :: " + finalPrepare.toString());
            prepare.executeUpdate();
        }catch (Exception e) {
            logger.error(() -> e);
            e.printStackTrace();
        }finally{
            try {
                assert prepare != null;
                prepare.close();
            } catch (SQLException throwable) {
                logger.error(() -> throwable);
                throwable.printStackTrace();
            }
        }
    }

    /**
     * Thanks to this method, all the sale_journal of the user will be deleted.
     */
    public static void deleteUsersSaleJournals(String userEmail){
        getConnection();
        PreparedStatement prepare = null;
        try{
            prepare = conn.prepareStatement("DELETE FROM sale_journal WHERE account_email= ?");
            prepare.setString(1, userEmail);
            PreparedStatement finalPrepare = prepare;
            logger.info(() -> "****** Trying to execute SQL request to delete user from sales journal :: " + finalPrepare.toString());
            prepare.executeUpdate();
        }catch (Exception e) {
            logger.error(() -> e);
            e.printStackTrace();
        }finally{
            try {
                assert prepare != null;
                prepare.close();
            } catch (SQLException throwable) {
                logger.error(() -> throwable);
                throwable.printStackTrace();
            }
        }
    }


}
