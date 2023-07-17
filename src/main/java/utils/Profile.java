package utils;

public class Profile{
    public static PropertiesManager propertiesManager;
    public static String ACCESS_MODE;
    public static String PLATFORM;
    public static String DEVICE_NAME;
    public static String OS_VERSION;
    public static String ENVIRONMENT;
    public static String APP_VERSION;
    public static String HOST;
    public static String PORT;
    public static String PROXY;
    public static String RETRY;

    static{
        setClassVariables();
    }

    private Profile(){
    }


    public static void instantiateVariables(){
        ACCESS_MODE     = get("ACCESS_MODE");
        PLATFORM        = get("PLATFORM");
        DEVICE_NAME     = get("DEVICE_NAME");
        OS_VERSION      = get("OS_VERSION");
        ENVIRONMENT     = get("ENVIRONMENT");
        APP_VERSION     = get("APP_VERSION");
        HOST            = get("HOST");
        PORT            = get("PORT");
        PROXY           = get("PROXY");
        RETRY           = get("RETRY");
    }

    public static void setClassVariables(){
        propertiesManager = new PropertiesManager("profile");
        instantiateVariables();
    }

    //ToDO:See If we can Improve the reset by using instance
    public static void reset(){
        propertiesManager = null;
        setClassVariables();
    }

    public static String get(String key){
        if(System.getProperty(key)!=null && !System.getProperty(key).isEmpty()) {
            return System.getProperty(key);
        }else{
            return propertiesManager.get(key.toUpperCase());
        }
    }

    public static void setAccessMode(String key){
        propertiesManager.writeInto("ACCESS_MODE", key);
        reset();
    }

    public static void setPLATFORM(String key){
        propertiesManager.writeInto("PLATFORM", key);
        reset();
    }

    public static void setDeviceName(String key){
        propertiesManager.writeInto("DEVICE_NAME", key);
        reset();
    }

    public static void setOsVersion(String key){
        propertiesManager.writeInto("OS_VERSION", key);
        reset();
    }

    public static void setENVIRONMENT(String key){
        propertiesManager.writeInto("ENVIRONMENT", key);
        reset();
    }

    public static void setAppVersion(String key){
        propertiesManager.writeInto("APP_VERSION", key);
        reset();
    }

    public static void setHOST(String key){
        propertiesManager.writeInto("HOST", key);
        reset();
    }

    public static void setPORT(String value) {
        propertiesManager.writeInto("PORT", value);
        reset();
    }

    public static void setPROXY(String value) {
        propertiesManager.writeInto("PROXY", value);
        reset();
    }

    public static void setRETRY(String value) {
        propertiesManager.writeInto("RETRY", value);
        reset();
    }


}
