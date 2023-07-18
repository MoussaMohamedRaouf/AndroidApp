package utils;

public class GlobalVariable {

    public static PropertiesManager propertiesManager;
    public static String EMAIL_SUPP;


    static{
        setClassVariables();
    }

    private GlobalVariable(){
    }


    public static void instantiateVariables(){
        EMAIL_SUPP                            = propertiesManager.get("EMAIL_SUPP");
    }

    public static void setClassVariables(){
        propertiesManager = new PropertiesManager("globalVariable");
        instantiateVariables();
    }

    public static String get(String key){
        return propertiesManager.get(key);
    }


    //ToDO:See If we can Improve the reset by using instance
    public static void reset(){
        propertiesManager = null;
        setClassVariables();
    }

}
