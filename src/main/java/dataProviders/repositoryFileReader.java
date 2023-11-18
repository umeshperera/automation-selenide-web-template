package dataProviders;

//Selenium and selenide imports
import com.codeborne.selenide.selector.ByAttribute;
import com.codeborne.selenide.selector.ByTagAndText;
import com.codeborne.selenide.selector.ByText;
import com.codeborne.selenide.selector.ByTextCaseInsensitive;
import org.openqa.selenium.By;

//Library import
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.*;

//Java imports
import java.util.ArrayList;

public class repositoryFileReader {
    /**
     * Find element information in the element repository (Excel sheet) using given 'ElementID'
     * @param worksheet Worksheet need to query for the information
     * @param elementIdentifier Information specified in the 'ElementID' column in Excel
     * @return Return String Arraylist with information in the ElementID,ElementPath,ByID columns in the Excel
     */
    public static ArrayList<String> findElementInRepo(String worksheet, String elementIdentifier){
        //Variables
        String locatorsRepositoryPath = constantConfigs.locatorsRepositoryPath;
        Fillo fillo = new Fillo();

        //Return arraylist
        ArrayList<String> locatorInformation = new ArrayList<>();

        //Query to execute
        String strQuery="SELECT * FROM "+worksheet+" WHERE ElementID = '"+elementIdentifier+"'";

        try{
            //Making connection to the file and executing query
            Connection connection=fillo.getConnection(locatorsRepositoryPath);
            Recordset recordset=connection.executeQuery(strQuery);

            while(recordset.next()){
                //Assigning locator details to the arraylist
                locatorInformation.add(recordset.getField("ElementID"));
                locatorInformation.add(recordset.getField("ElementPath"));
                locatorInformation.add(recordset.getField("ByID"));
            }

            //Closing connection to prevent memory leaks
            recordset.close();
            connection.close();

            //Returning locator information arraylist
            return locatorInformation;
        } catch(FilloException e){
            //Throw an error for Excel read errors
            throw new RuntimeException("Exception occurred while reading locator repository excel file",e);
        }
    }

    /**
     * Construct element using 'By' class using the given element information
     * @param locatorInformation Arraylist which contains all the information related to the element
     * @return Return locator constructed using 'By' class
     * <pre>Example usage - "By element = constructElement(findElementInRepo("WORKSHEET NAME","ELEMENT_ID"));"
     * <br>Valid locator types for 'ById' column in Excel sheet !IMPORTANT case sensitive
     * <br>(By.className,By.cssSelector,By.id,By.name,By.linkText,By.partialLinkText,By.tagName,By.text,By.textCaseInsensitive,By.xpath)</pre>
     */
    public static By constructElement(ArrayList<String> locatorInformation){

        if (locatorInformation.isEmpty()){
            //Throw error for locators not available on Excel sheet
            throw new RuntimeException("The given locator not present in the locatorRepository");
        } else if (locatorInformation.size() > 3) {
            //Throw error for 'ElementID' duplication
            throw new RuntimeException("Multiple locators available with same 'ElementID'. Possible duplication.");
        } else {
            //Get 'ByID' column value
            String byID = locatorInformation.get(2);
            //Get 'ElementPath' column value
            String elementPath = locatorInformation.get(1);

            //Construct element using 'By' class based on given value in 'ById' column
            switch (byID) {
                case "By.className" -> {
                    return new By.ByClassName(elementPath);
                }
                case "By.cssSelector" -> {
                    return new By.ByCssSelector(elementPath);
                }
                case "By.id" -> {
                    return new By.ById(elementPath);
                }
                case "By.name" -> {
                    return new By.ByName(elementPath);
                }
                case "By.linkText" -> {
                    return new By.ByLinkText(elementPath);
                }
                case "By.partialLinkText" -> {
                    return new By.ByPartialLinkText(elementPath);
                }
                case "By.tagName" -> {
                    return new By.ByTagName(elementPath);
                }
                case "By.text" -> {
                    return new ByText(elementPath);
                }
                case "By.textCaseInsensitive" -> {
                    return new ByTextCaseInsensitive(elementPath);
                }
                case "By.xpath" -> {
                    return new By.ByXPath(elementPath);
                }
                default -> throw new RuntimeException(byID+" locator type is unknown");
            }
        }
    }

    /**
     * Construct element using 'By' class using the given element information
     * @param attributes Arraylist which contains all the attribute information related to the element (attributeName,tag)
     * @param attributeValue Attribute value required for creation of the locator
     * @return Return locator constructed using 'By' class
     * <pre>Example usage - "By element = constructElement(findElementInRepo("WORKSHEET NAME","ELEMENT_ID"),attributeValues);"
     * <br>Valid locator types for 'ById' column in Excel sheet !IMPORTANT case sensitive
     * <br>(By.attribute,By.ByTagAndText)</pre>
     */
    public static By constructElement(ArrayList<String> attributes, String attributeValue){

        if (attributes.isEmpty()){
            //Throw error for locators not available on Excel sheet
            throw new RuntimeException("The given locator not present in the locatorRepository");
        } else if (attributes.size() > 3) {
            //Throw error for 'ElementID' duplication
            throw new RuntimeException("Multiple locators available with same 'ElementID'. Possible duplication.");
        } else {
            //Get 'ByID' column value
            String byID = attributes.get(2);
            //Get 'ElementPath' column value
            String elementPath = attributes.get(1);

            //Construct element using 'By' class based on given value in 'ById' column
            switch (byID) {
                case "By.attribute" -> {
                    return new ByAttribute(elementPath,attributeValue);
                }
                case "By.tagAndText" -> {
                    return new ByTagAndText(elementPath,attributeValue);
                }
                default -> throw new RuntimeException(byID+" locator type is unknown");
            }
        }
    }

    /**
     * Construct element using 'By' class using the given element information
     * @param attributes Arraylist which contains all the attributes related to the element
     * @param attributeValues Arraylist which contains all the values related to the attributes
     * @return Return locator constructed using 'By' class
     * <pre>Example usage - "By element = constructElement(findElementInRepo("WORKSHEET NAME","ELEMENT_ID"),attributeValues);"
     * <br>Valid locator types for 'ById' column in Excel sheet !IMPORTANT case sensitive
     * <br>(By.attributes)</pre>
     */
    public static By constructElement(ArrayList<String> attributes, ArrayList<String> attributeValues){

        if (attributes.isEmpty()){
            //Throw error for locators not available on Excel sheet
            throw new RuntimeException("The given locator not present in the locatorRepository");
        } else if (attributes.size() > 3) {
            //Throw error for 'ElementID' duplication
            throw new RuntimeException("Multiple locators available with same 'ElementID'. Possible duplication.");
        } else {
            //Get 'ByID' column value
            String byID = attributes.get(2);
            //Get 'ElementPath' column value and split additional properties based on ',' character
            String[] elementPath = attributes.get(1).split(",");

            //Checking 'ByID' column value
            if(byID.equals("By.attributes")){
                //Comparing attributes count and count of the given values for the specific attributes
                if(attributeValues.size() == elementPath.length){
                    //Initiating String Buffer
                    StringBuilder attributesXpath = new StringBuilder();
                    //Loop through attributes and values
                    for(int index = 0; index < elementPath.length ; index++){
                        //Constructing xpath based on attributes and values provided
                        attributesXpath.append("[@").append(elementPath[index]).append("=").append('"').append(attributeValues.get(index)).append('"').append("]");
                    }
                    //Construct element using 'By' class based on constructed xpath
                    return new By.ByXPath("//*"+attributesXpath);
                } else {
                    //Throwing error when given attributes count and values provided not equal
                    throw new RuntimeException("Number of given attributes and values provided not matched");
                }
            } else {
                //Throwing error when given locator type is unknown
                throw new RuntimeException(byID+" locator type is unknown");
            }
        }
    }

    /**
     * Construct element using 'By' class, utilizing the given element information (base xpath + additional attributes and values)
     * <br> Utilize 'following' concept in xpath to create final Xpath
     * @param baseXpath Arraylist which contains information related to the base xpath
     * @param attributes Arraylist which contains all the attributes related to the element
     * @param attributeValues Arraylist which contains all the values related to the attributes
     * @return Return locator constructed using 'By' class
     * <pre>Example usage - "By element = constructElement(baseXpath,findElementInRepo("WORKSHEET NAME","ELEMENT_ID"),attributeValues);"
     * <br>Valid locator types for 'ById' column in Excel sheet !IMPORTANT case sensitive
     * <br>(By.attributes)</pre>
     */
    public static By constructElement(ArrayList<String> baseXpath, ArrayList<String> attributes, ArrayList<String> attributeValues){

        if (baseXpath.isEmpty() && attributes.isEmpty()){
            //Throw error for locators not available on Excel sheet
            throw new RuntimeException("The given locators not present in the locatorRepository");
        } else if (baseXpath.size() > 3 && attributes.size() > 3) {
            //Throw error for 'ElementID' duplication
            throw new RuntimeException("Multiple locators available with same 'ElementID'. Possible duplication.");
        } else {
            //Get 'ByID' column value from baseXpath
            String baseXpathByID = baseXpath.get(2);
            //Get 'ElementPath' column value from baseXpath
            String baseXpathElementPath = baseXpath.get(1);
            //Get 'ByID' column value
            String attributesByID = attributes.get(2);
            //Get 'ElementPath' column value and split additional properties based on ',' character
            String[] attributesElementPath = attributes.get(1).split(",");

            //Checking 'ByID' column value
            if(baseXpathByID.equals("By.xpath") && attributesByID.equals("By.attributes")){
                //Comparing attributes count and count of the given values for the specific attributes
                if(attributeValues.size() == attributesElementPath.length){
                    //Initiating String Buffer
                    StringBuilder attributesXpath = new StringBuilder();
                    //Loop through attributes and values
                    for(int index = 0; index < attributesElementPath.length ; index++){
                        //Constructing xpath based on attributes and values provided
                        attributesXpath.append("[@").append(attributesElementPath[index]).append("=").append('"').append(attributeValues.get(index)).append('"').append("]");
                    }
                    //Construct element using 'By' class based on constructed xpath
                    return new By.ByXPath(baseXpathElementPath+"//following::*"+attributesXpath);
                } else {
                    //Throwing error when given attributes count and values provided not equal
                    throw new RuntimeException("Number of given attributes and values provided not matched");
                }
            } else {
                //Throwing error when given locator type is unknown
                throw new RuntimeException("Provided locator types, not compatible to construct full Xpath");
            }
        }
    }
}