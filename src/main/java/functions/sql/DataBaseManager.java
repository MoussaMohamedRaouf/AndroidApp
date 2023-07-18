package functions.sql;

import functions.excel.ExcelManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.EnvironmentVariable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseManager {
    private static Logger logger = LogManager.getLogger(DataBaseManager.class);

    private DataBaseManager(){
    }


    /***
     * Initiate the connexion
     */
    public static Connection connectDB(String dataBaseName){
        String server = "10.231.0.49:";
        String accessMode = System.getProperty("DB_ACCESS_MODE", "passhport");
        if (accessMode.equals("direct")) {
            if (EnvironmentVariable.ENV.equals("UAT")) {
                server = "lx1156.res.jcdecaux.org:5432";
            } else if(EnvironmentVariable.ENV.equals("INT")){
                server = "lx1832.res.jcdecaux.org:5432";
            }
        } else {
            server += getPort();
        }
        Properties props = new Properties();
        String password = KeyStoreManager.readKeyStore(EnvironmentVariable.ENV+dataBaseName);
        String url = "jdbc:postgresql://" + server +"/"+dataBaseName+"?currentSchema="+dataBaseName+"_own";
        props.setProperty("user", dataBaseName+"_own");
        props.setProperty("password", password);
        try {
           return DriverManager.getConnection(url,props);
        } catch (SQLException e) {
            logger.error(() -> e);
            e.printStackTrace();
        }
        return null;
    }

    /***
     * Close the connection.
     * We have to call this method at the end of each test case.
     */
    public static Connection closeDB(Connection conn) {
        try {
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        } catch (SQLException throwable) {
            logger.error(() -> throwable);
            throwable.printStackTrace();
        }
        conn = null;
        return conn;
    }

    /***
     * Get the port on the Logins File depending on the profile.
     */
    public static String getPort(){
        ExcelManager loginsFile = new ExcelManager("Logins/Logins.xlsx","logins",1);
        if(EnvironmentVariable.ENV.equals("UAT")){
            return loginsFile.read("portPGAdminUAT");
        }else if(EnvironmentVariable.ENV.equals("INT")){
            return loginsFile.read("portPGAdminINT");
        }
        return null;
    }


}
