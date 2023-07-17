package utils;

public class EnvironmentVariable {

    public static PropertiesManager propertiesManager;
    public static String CONTRACT_NAME;
    public static String CONTRACT_CODE;
    public static String ENV_API;
    public static String ENV;
    public static String GENERIC_EMAIL;
    public static String GENERIC_PASSWORD;
    public static String CC_EMAIL;
    public static String ONECLIC_IBAN_EMAIL;
    public static String IBAN_EMAIL;
    public static String RECEIPT_EMAIL;
    public static String COMPL_EMAIL;
    public static String BADGE_EMAIL;
    public static String RENEWAL_EMAIL;
    public static String CGAU_EMAIL;
    public static String ACM_GENERIC_EMAIL;
    public static String BATTERY_EMAIL;
    public static String SIGNUP_EMAIL;
    public static String WRONG_EMAIL;
    public static String ST_GROUP;
    public static String LT_GROUP;
    public static String PROOF_GROUP;
    public static String VLD_GROUP;
    public static String PARKING_GROUP;
    public static String EVLS_GROUP;
    public static String PROFESSIONAL_GROUP;
    public static String ST_OFFER;
    public static String LT_OFFER;
    public static String PROOF_OFFER;
    public static String VLD_OFFER;
    public static String FREE_PARK_OFFER;
    public static String PAYABLE_PARK_OFFER;
    public static String EVLS_OFFER;
    public static String PROFESSIONAL_OFFER;
    public static String BLOCKING_ST_OFFER;
    public static String ST_OFFER_ID;
    public static String LT_OFFER_ID;
    public static String PROOF_OFFER_ID;
    public static String VLD_OFFER_ID;
    public static String FREE_PARK_OFFER_ID;
    public static String PAYABLE_PARK_OFFER_ID;
    public static String EVLS_OFFER_ID;
    public static String PRO_OFFER_ID;
    public static String BLOCKING_ST_OFFER_ID;
    public static String VIP_OFFER_ID;
    public static String PROFESSIONAL_OFFER_ID;
    public static String EXTERNAL_BADGE;
    public static String OWNER_BADGE;
    public static String TICKET_BADGE;
    public static String BLOCKING_BADGE;
    public static String EMPTY_BADGE;
    public static String EXTERNAL_BADGE_ID;
    public static String OWNER_BADGE_ID;
    public static String TICKET_BADGE_ID;
    public static String BLOCKING_BADGE_ID;
    public static String EMPTY_BADGE_ID;
    public static String BIKE_ID;
    public static String PARK_ID;
    public static String VAE_ID;
    public static String GENERIC_PROMO;
    public static String VIP_POSITION;
    public static String ENT_POSITION;

    static{
        setClassVariables();
    }

    private EnvironmentVariable(){
    }


    public static void instantiateVariables(){
        CONTRACT_NAME           = propertiesManager.get("CONTRACT_NAME");
        CONTRACT_CODE           = propertiesManager.get("CONTRACT_CODE");
        ENV_API                 = propertiesManager.get("ENV_API");
        ENV                     = propertiesManager.get("ENV");
        GENERIC_EMAIL           = propertiesManager.get("GENERIC_EMAIL");
        GENERIC_PASSWORD        = propertiesManager.get("GENERIC_PASSWORD");
        CC_EMAIL                = propertiesManager.get("CC_EMAIL");
        ONECLIC_IBAN_EMAIL      = propertiesManager.get("ONECLIC_IBAN_EMAIL");
        IBAN_EMAIL              = propertiesManager.get("IBAN_EMAIL");
        RECEIPT_EMAIL           = propertiesManager.get("RECEIPT_EMAIL");
        COMPL_EMAIL             = propertiesManager.get("COMPL_EMAIL");
        BADGE_EMAIL             = propertiesManager.get("BADGE_EMAIL");
        RENEWAL_EMAIL           = propertiesManager.get("RENEWAL_EMAIL");
        CGAU_EMAIL              = propertiesManager.get("CGAU_EMAIL");
        ACM_GENERIC_EMAIL       = propertiesManager.get("ACM_GENERIC_EMAIL");
        BATTERY_EMAIL           = propertiesManager.get("BATTERY_EMAIL");
        SIGNUP_EMAIL            = propertiesManager.get("SIGNUP_EMAIL");
        WRONG_EMAIL             = propertiesManager.get("WRONG_EMAIL");
        ST_GROUP                = propertiesManager.get("ST_GROUP");
        LT_GROUP                = propertiesManager.get("LT_GROUP");
        PROOF_GROUP             = propertiesManager.get("PROOF_GROUP");
        VLD_GROUP               = propertiesManager.get("VLD_GROUP");
        PARKING_GROUP           = propertiesManager.get("PARKING_GROUP");
        EVLS_GROUP              = propertiesManager.get("EVLS_GROUP");
        PROFESSIONAL_GROUP      = propertiesManager.get("PROFESSIONAL_GROUP");
        ST_OFFER                = propertiesManager.get("ST_OFFER");
        LT_OFFER                = propertiesManager.get("LT_OFFER");
        PROOF_OFFER             = propertiesManager.get("PROOF_OFFER");
        VLD_OFFER               = propertiesManager.get("VLD_OFFER");
        FREE_PARK_OFFER         = propertiesManager.get("FREE_PARK_OFFER");
        PAYABLE_PARK_OFFER      = propertiesManager.get("PAYABLE_PARK_OFFER");
        EVLS_OFFER              = propertiesManager.get("EVLS_OFFER");
        PROFESSIONAL_OFFER      = propertiesManager.get("PROFESSIONAL_OFFER");
        BLOCKING_ST_OFFER       = propertiesManager.get("BLOCKING_ST_OFFER");
        ST_OFFER_ID             = propertiesManager.get("ST_OFFER_ID");
        LT_OFFER_ID             = propertiesManager.get("LT_OFFER_ID");
        PROOF_OFFER_ID          = propertiesManager.get("PROOF_OFFER_ID");
        VLD_OFFER_ID            = propertiesManager.get("VLD_OFFER_ID");
        FREE_PARK_OFFER_ID      = propertiesManager.get("FREE_PARK_OFFER_ID");
        PAYABLE_PARK_OFFER_ID   = propertiesManager.get("PAYABLE_PARK_OFFER_ID");
        EVLS_OFFER_ID           = propertiesManager.get("EVLS_OFFER_ID");
        PRO_OFFER_ID            = propertiesManager.get("PRO_OFFER_ID");
        BLOCKING_ST_OFFER_ID    = propertiesManager.get("BLOCKING_ST_OFFER_ID");
        VIP_OFFER_ID            = propertiesManager.get("VIP_OFFER_ID");
        PROFESSIONAL_OFFER_ID   = propertiesManager.get("PROFESSIONAL_OFFER_ID");
        EXTERNAL_BADGE          = propertiesManager.get("EXTERNAL_BADGE");
        OWNER_BADGE             = propertiesManager.get("OWNER_BADGE");
        TICKET_BADGE            = propertiesManager.get("TICKET_BADGE");
        BLOCKING_BADGE          = propertiesManager.get("BLOCKING_BADGE");
        EMPTY_BADGE             = propertiesManager.get("EMPTY_BADGE");
        EXTERNAL_BADGE_ID       = propertiesManager.get("EXTERNAL_BADGE_ID");
        OWNER_BADGE_ID          = propertiesManager.get("OWNER_BADGE_ID");
        TICKET_BADGE_ID         = propertiesManager.get("TICKET_BADGE_ID");
        BLOCKING_BADGE_ID       = propertiesManager.get("BLOCKING_BADGE_ID");
        EMPTY_BADGE_ID          = propertiesManager.get("EMPTY_BADGE_ID");
        BIKE_ID                 = propertiesManager.get("BIKE_ID");
        PARK_ID                 = propertiesManager.get("PARK_ID");
        VAE_ID                  = propertiesManager.get("VAE_ID");
        GENERIC_PROMO           = propertiesManager.get("GENERIC_PROMO");
        VIP_POSITION            = propertiesManager.get("VIP_POSITION");
        ENT_POSITION            = propertiesManager.get("ENT_POSITION");
    }

    public static void setClassVariables(){
        String ENVIRONMENT = getEnvironment();
        propertiesManager   = new PropertiesManager("environments/"+ ENVIRONMENT);
        instantiateVariables();
    }

    //ToDO:See If we can Improve the reset by using instance
    public static void reset(){
        propertiesManager = null;
        setClassVariables();
    }

    public static String get(String key){
        return propertiesManager.get(key);
    }
    public static void set(String key,String value){
        propertiesManager.writeInto(key,value);
    }

    public static String getEnvironment(){
            return Profile.ENVIRONMENT.toLowerCase();
    }


}
