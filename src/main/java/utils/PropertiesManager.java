package utils;

import exceptions.NotFoundPathException;
import functions.miscellaneous.PathFinder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    protected Properties prop;
    protected String pathToProperties;

    public PropertiesManager(String propertiesFileName){
        pathToProperties = PathFinder.getPath("properties/"+propertiesFileName+".properties");
        setProperties();
    }

    /**
     * This method load all the data at the first time that the static class is called.
     */
    public void setProperties(){
        if(prop == null){
            prop = new Properties();
            dataLoader();
        }
    }

    /**
     * Load all the file's data and  put it in the instance variable
     */
    public void dataLoader(){
        try(FileInputStream fi = new FileInputStream(pathToProperties)) {
            prop.load(fi);
        } catch (IOException e) {
            e.printStackTrace();
            throw new NotFoundPathException("globalVariable.properties not found at "+ pathToProperties );
        }
    }

    /**
     * Write and save a data on the propile file.
     * @param key
     * @param value
     */
    public void writeInto(String key, String value){
        prop.setProperty(key, value);
        try(FileOutputStream fo = new FileOutputStream(pathToProperties)) {
            prop.store(fo, null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new NotFoundPathException("globalVariable.properties not found at "+ pathToProperties );
        }
    }

    /**
     * If the property exists in the properties file, this method will return it, otherwise it will return the input itself
     * @param property
     * @return
     */
    public String get(String property){
        if(prop.getProperty(property)!=null && !prop.getProperty(property).isEmpty()){
            return prop.getProperty(property);
        }else{
            return property;
        }
    }



}
