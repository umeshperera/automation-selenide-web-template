package dataProviders;

//Imports
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class datasetReader {

    /**
     * Retrieve 'String' data value based on any given key
     * @param key Key which need to be searched in the dataSet file
     * @return String value of the given key
     */
    public static String getDataSetValue(String key) {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName(constantConfigs.dataSetPath));
        try {
            Configuration config = builder.getConfiguration();
            //Check if given key available
            if (config.getString(key) == null) {
                throw new RuntimeException("The given property key ("+key+") not specified in the dataSet.properties file");
            } else {
                return config.getString(key);
            }
        } catch (ConfigurationException e) {
            throw new RuntimeException("Exception occurred while reading dataset.properties" + e);
        }
    }

    /**
     * Update existing value in the dataset.properties base on provided key
     * @param key   Key which need to be searched in the dataSet file
     * @param value Updated value
     */
    public static void updateDataSetValue(String key, String value) {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName(constantConfigs.dataSetPath));
        try {
            Configuration config = builder.getConfiguration();
            //Check if given key available
            if (config.getString(key) == null) {
                throw new RuntimeException("The given property key ("+key+") not specified in the dataSet.properties file");
            } else {
                config.setProperty(key, value);
                builder.save();
            }
        } catch (ConfigurationException e) {
            throw new RuntimeException("Exception occurred while updating dataset.properties" + e);
        }
    }
}