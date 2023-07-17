package functions.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccounts {
    private static Connection conn;
    private static final Logger logger = LogManager.getLogger(DBAccounts.class);

    /***
     * Make the constructor private to not be able to instantiate it
     */
    private DBAccounts(){
    }

    /***
     * Generate A new connection if the current one is null on the first call.
     */
    private static void getConnection(){
        if(conn==null){
            logger.info("****** Trying to connect to Account DB");
            conn = DataBaseManager.connectDB("accounts");
            logger.info("****** Account DB connected");
        }
    }

    /***
     * Call closeDB method to close the bdd the to conn parameter
     */
    public static void closeDBAccounts(){
        if(conn!=null){
            logger.info("****** Trying to close Account DB");
            conn = DataBaseManager.closeDB(conn);
            logger.info("****** Account DB closed");
        }
    }

    /***
     * By using this method, the account will be reset so that it will be possible to complete it again.
     * @param userEmail
     */
    public static void resetCompletion(String userEmail){
        getConnection();
        PreparedStatement prepare = null;
        try {
            prepare = conn.prepareStatement("UPDATE account SET last_name = NULL WHERE  email= ?");
            prepare.setString(1,userEmail);
            PreparedStatement finalPrepare = prepare;
            logger.info(() -> "****** Trying to execute SQL request used for completion :: " + finalPrepare.toString());
            prepare.executeUpdate();
        } catch (SQLException throwables) {
            logger.error(() -> throwables);
            throwables.printStackTrace();
        }finally {
            try {
                assert prepare != null;
                prepare.close();
            } catch (SQLException throwables) {
                logger.error(() -> throwables);
                throwables.printStackTrace();
            }
        }
    }

    /**
     * Using this method, we can change the validity of the subscription. By doing so, we are able of purchasing a re-subscription
     */
    public static void advanceSubscriptionPeriod(String subscriptionPeriodId, int numberOfDaysToAdvance) {
        getConnection();
        String request ="UPDATE subscription_periods SET validity_start=validity_start - INTERVAL '"+
                numberOfDaysToAdvance+" day', validity_end=validity_end - INTERVAL '"+
                numberOfDaysToAdvance+" day', updated_at=current_date WHERE id='"+subscriptionPeriodId+"'";
        Statement state = null;
        try {
            state = conn.createStatement();
            logger.info(() -> "****** Trying to execute SQL request used for advancing subscription dates :: " + request);
            state.executeUpdate(request);
        } catch (SQLException throwable) {
            logger.error(() -> throwable);
            throwable.printStackTrace();
        }finally{
            try {
                assert state != null;
                state.close();
            } catch (SQLException throwable) {
                logger.error(() -> throwable);
                throwable.printStackTrace();
            }
        }
    }


}
