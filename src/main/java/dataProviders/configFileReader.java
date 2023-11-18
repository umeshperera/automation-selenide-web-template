package dataProviders;

//Selenium imports
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

//Java and library Imports
import java.util.Iterator;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class configFileReader {
    /**
     * This method used to retrieve 'String' property value for any given property key
     * @param propertyKey Property key which need to be searched in the property file
     * @return String value of the given property key
     */
    public static String getPropertyValue(String propertyKey){
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName(constantConfigs.propertyFilePath));
        try {
            Configuration config = builder.getConfiguration();
            //Check if given key available
            if (config.getString(propertyKey) == null) {
                throw new RuntimeException("The given property ("+propertyKey+") not specified in the configuration.properties file");
            } else {
                return config.getString(propertyKey);
            }
        } catch (ConfigurationException e) {
            throw new RuntimeException("Exception occurred while reading dataset.properties" + e);
        }
    }

    /**
     * This method used to retrieve 'Long' data type property value for any given property key
     * @param propertyKey Property key which need to be searched in the property file
     * @return Long value of the given property key
     */
    public static Long getLongPropertyValue(String propertyKey){
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName(constantConfigs.propertyFilePath));
        try {
            Configuration config = builder.getConfiguration();
            //Check if given key available
            if (config.getString(propertyKey) == null) {
                throw new RuntimeException("The given property ("+propertyKey+") not specified in the configuration.properties file");
            } else {
                return config.getLong(propertyKey);
            }
        } catch (ConfigurationException e) {
            throw new RuntimeException("Exception occurred while reading dataset.properties" + e);
        }
    }

    /**
     * This method used to retrieve 'Integer' data type property value for any given property key
     * @param propertyKey Property key which need to be searched in the property file
     * @return Integer value of the given property key
     */
    public static Integer getIntegerPropertyValue(String propertyKey){
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName(constantConfigs.propertyFilePath));
        try {
            Configuration config = builder.getConfiguration();
            //Check if given key available
            if (config.getString(propertyKey) == null) {
                throw new RuntimeException("The given property ("+propertyKey+") not specified in the configuration.properties file");
            } else {
                return config.getInt(propertyKey);
            }
        } catch (ConfigurationException e) {
            throw new RuntimeException("Exception occurred while reading dataset.properties" + e);
        }
    }

    /**
     * This method used to retrieve 'Float' data type property value for any given property key
     * @param propertyKey Property key which need to be searched in the property file
     * @return Float value of the given property key
     */
    public static Float getFloatPropertyValue(String propertyKey){
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName(constantConfigs.propertyFilePath));
        try {
            Configuration config = builder.getConfiguration();
            //Check if given key available
            if (config.getString(propertyKey) == null) {
                throw new RuntimeException("The given property ("+propertyKey+") not specified in the configuration.properties file");
            } else {
                return config.getFloat(propertyKey);
            }
        } catch (ConfigurationException e) {
            throw new RuntimeException("Exception occurred while reading dataset.properties" + e);
        }
    }

    /**
     * This method used to retrieve 'Boolean' property value for any given property key
     * @param propertyKey Property key which need to be searched in the property file
     * @return Boolean value of the given property key
     */
    public static boolean getBooleanPropertyValue(String propertyKey){
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName(constantConfigs.propertyFilePath));
        try {
            Configuration config = builder.getConfiguration();
            //Check if given key available
            if (config.getString(propertyKey) == null) {
                throw new RuntimeException("The given property ("+propertyKey+") not specified in the configuration.properties file");
            } else {
                return config.getBoolean(propertyKey);
            }
        } catch (ConfigurationException e) {
            throw new RuntimeException("Exception occurred while reading dataset.properties" + e);
        }
    }

    /**
     * This method used to retrieve property value related to the Chrome browser capabilities
     * @return ChromeOptions object with all the properties mentioned in the config<br>
     * Add config with 'chromeCapabilities.**capability name**="**Value**"'
     */
    public static ChromeOptions getChromeBrowserCapabilities(){
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName(constantConfigs.propertyFilePath));

        try {
            //Creating ChromeOption object to hold custom configs
            ChromeOptions chromeBrowserCapabilities = new ChromeOptions();

            //Creating config object
            Configuration config = builder.getConfiguration();

            //Get all property keys and creating iterator
            Iterator<?> it = config.getKeys();

            //Looping through properties
            while(it.hasNext()) {
                //Get property key as string
                String propertyKey = it.next().toString();
                //Filter out properties based on starting keyword
                if (propertyKey.startsWith("chromeCapabilities")) {
                    chromeBrowserCapabilities.addArguments(config.getString(propertyKey));
                }
            }
            return chromeBrowserCapabilities;
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method used to retrieve property value related to the firefox browser capabilities
     * @return FirefoxOptions object with all the properties mentioned in the config<br>
     * Add config with 'firefoxCapabilities.**capability name**="**Value**"'
     */
    public static FirefoxOptions getFirefoxBrowserCapabilities(){
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName(constantConfigs.propertyFilePath));

        try {
            //Creating FirefoxOption object to hold custom configs
            FirefoxOptions firefoxBrowserCapabilities = new FirefoxOptions();

            //Creating config object
            Configuration config = builder.getConfiguration();

            //Get all property keys and creating iterator
            Iterator<?> it = config.getKeys();

            //Looping through properties
            while(it.hasNext()) {
                //Get property key as string
                String propertyKey = it.next().toString();
                //Filter out properties based on starting keyword
                if (propertyKey.startsWith("firefoxCapabilities")) {
                    firefoxBrowserCapabilities.addArguments(config.getString(propertyKey));
                }
            }
            return firefoxBrowserCapabilities;
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}