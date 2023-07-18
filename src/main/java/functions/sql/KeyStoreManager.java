package functions.sql;

import functions.excel.ExcelManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

public class KeyStoreManager {
    private static final ExcelManager loginsFile = new ExcelManager("Logins/Logins.xlsx","logins",1);
    private static final char[] pwdArray = loginsFile.read("passportKS").toCharArray();
    private static KeyStore ks;
    private static final Logger logger = LogManager.getLogger(KeyStoreManager.class);

    private KeyStoreManager(){
    }


    /***
     * Thanks to this method, we load the keystore file and put it into the class variable.
     */
    private static void getKeyStore(){
      if(ks==null) {
          try {
              ks = KeyStore.getInstance("jceks");
              ks.load(new FileInputStream("DBKeyStore.jceks"),pwdArray);
          } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException e) {
              logger.error("Something went wrong while loading keystore file :: ", e);
              e.printStackTrace();
          }
      }
    }

    /***
     * Method to create a new keystore file.
     */
    public static void createKeyStore(String nameOfKeyStore){
        try {
           KeyStore ks = KeyStore.getInstance("jceks");
           ks.load(null, pwdArray);
           FileOutputStream fos = new FileOutputStream(nameOfKeyStore+".jceks");
           ks.store(fos, pwdArray);
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException e) {
            logger.error("Something went wrong while creating new keystore :: ", e);
            e.printStackTrace();
        }
    }

    /***
     * Method to add a new secret key and its alias on the DBKeyStore.jceks file.
     */
    public static void addKey(String alias, String secretKey){
        //Load the DBKeyStore.jceks
        getKeyStore();
        //Load the DBKeyStore.jceks
        KeyStore.ProtectionParameter password = new KeyStore.PasswordProtection(pwdArray);
        // Transform the secret key string to a SecretKey object. DSA is the algorithm used.
        SecretKey mySecretKey = new SecretKeySpec(secretKey.getBytes(), "DSA");
        // Only the secretKey object matches the KeyStore.SecretKeyEntry
        KeyStore.SecretKeyEntry secret = new KeyStore.SecretKeyEntry(mySecretKey);
        // Save the secret key and its alias and give the password to release it.
        try {
            ks.setEntry(alias, secret, password);
            FileOutputStream fos = new FileOutputStream("DBKeyStore.jceks");
            ks.store(fos, pwdArray);
            fos.flush();
            fos.close();
        } catch (CertificateException | NoSuchAlgorithmException | IOException | KeyStoreException e) {
            logger.error("Something went wrong while adding new key :: ", e);
            e.printStackTrace();
        }
    }

    /**
     * By providing this alias, this method will return the value.
     * @param alias key to read
     * @return value of the key
     */
    public static String readKeyStore(String alias){
        getKeyStore();
        // format the password in order to access the particular alias key.
        KeyStore.ProtectionParameter password = new KeyStore.PasswordProtection(pwdArray);
        // Access and get the wanted entry.
        KeyStore.SecretKeyEntry secretKeyEnt = null;
        try {
            secretKeyEnt = (KeyStore.SecretKeyEntry)ks.getEntry(alias, password);
        } catch (NoSuchAlgorithmException |UnrecoverableEntryException | KeyStoreException e) {
            logger.error("Something went wrong while reading key :: ", e);
            e.printStackTrace();
        }
        // From the entry, get the secret key.
        assert secretKeyEnt != null;
        SecretKey mySecretKey = secretKeyEnt.getSecretKey();
        // Return the key as a string
        return new String(mySecretKey.getEncoded());
    }

    /***
     * This method is a reminder of the alias used.
     */
    public static void printAlias(){
        String[] alias = {"INTinvoices", "UATinvoices", "INTaccounts", "UATaccounts"};
        for(String a : alias){
            logger.info(() -> ("****** alias : "+ a));
        }
    }


}
