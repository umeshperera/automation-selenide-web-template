package base;

//Selenide, cucumber and JUnit imports
import com.codeborne.selenide.DownloadOptions;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.TypeOptions;
import io.cucumber.java.Scenario;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import static org.openqa.selenium.Keys.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

//Java imports
import java.io.*;
import java.net.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Library imports
import net.datafaker.Faker;
import net.datafaker.providers.base.Finance;

//Custom imports
import dataProviders.constantConfigs;
import static dataProviders.datasetReader.getDataSetValue;
import static dataProviders.datasetReader.updateDataSetValue;
import static dataProviders.repositoryFileReader.constructElement;
import static dataProviders.repositoryFileReader.findElementInRepo;

public class webExecution {

    //Faker object
    Faker faker = new Faker();

    /**
     * Open given URL on browser
     * @param URL URL need to be opened
     * @param scenario Current scenario object
     */
    public void openURL(String URL,Scenario scenario){
        //Getting URL from dataSet.properties
        String URLValue = getDataSetValue(URL);
        //Logging data to the cucumber report
        scenario.log(URL+": "+URLValue);
        //Open URL
        open(URLValue);
    }

    /**
     * Update value in dataset with retrieved value from the element provided
     * @param locator Element locator
     * @param key Key which need to be searched in the dataSet file
     */
    public void updateDataset(String locator,String key){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Update dataset
        updateDataSetValue(key,$(element).getValue());
    }

    /**
     * Type given text into the element provided
     * @param text Text need to be typed
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void typeText(String text,String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By textArea = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get text from dataSet.properties
        String textValue = getDataSetValue(text);
        //Logging data to the cucumber report
        scenario.log(text+": "+textValue);
        //Perform action
        $(textArea).should(visible).click();
        $(textArea).val(textValue);
    }

    /**
     * Emulate actual user input by keyboard and enter given text to the element provided
     * @param text Text need to be typed
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void typeTextAndSearch(String text,String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By textArea = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get text from dataSet.properties
        String textValue = getDataSetValue(text);
        //Logging data to the cucumber report
        scenario.log(text+": "+textValue);
        //Perform action
        $(textArea).type(TypeOptions.text(textValue).clearFirst(true));
    }

    /**
     * Type exact text given into the element provided
     * @param text Exact text need to be typed
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void typeExactText(String text,String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By textArea = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Logging data to the cucumber report
        scenario.log("Typed Text: "+text);
        //Perform action
        $(textArea).val(text);
    }

    /**
     * Type given text to the dropdown
     * @param text Text need to be typed
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void typeTextToDropdown(String text,String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By dropDown = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get text from dataSet.properties
        String textValue = getDataSetValue(text);
        //Logging data to the cucumber report
        scenario.log(text+": "+textValue);
        //Perform action
        $(dropDown).val(textValue+"\n");
    }

    /**
     * Select given text from the dropdown
     * @param text Text need to be selected
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void selectTextFromDropdown(String text,String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By dropDown = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get text from dataSet.properties
        String textValue = getDataSetValue(text);
        //Logging data to the cucumber report
        scenario.log(text+": "+textValue);
        //Perform action
        $(dropDown).selectOption(textValue);
    }

    /**
     * Search given text from the dropdown and select
     * @param text Text need to be selected
     * @param locator1 Loading element locator
     * @param locator2 Dropdown element locator
     * @param scenario Current scenario object
     */
    public void searchTextFromDropdown(String text,String locator1,String locator2,Scenario scenario){
        //Constructing locator related to the given locator information
        By loading = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator1));
        By dropDown = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator2));
        //Get text from dataSet.properties
        String textValue = getDataSetValue(text);
        //Logging data to the cucumber report
        scenario.log(text+": "+textValue);
        //Perform action
        $(dropDown).val(textValue);
        $(loading).shouldNot(visible);
        $(dropDown).pressTab();
    }

    /**
     * Select current date from the date picker
     * Date format "MM/DD/YYYY" (Ex: 11/16/2023)
     * @param locator Attribute name
     */
    public void selectCurrentDate(String locator){
        //Format current date
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        //Constructing locator related to the given locator information
        By datePicker = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));

        //Select date from the date picker
        $(datePicker).setValue(formattedDate);
    }

    /**
     * Select next date from the current day in date picker
     * Date format "MM/DD/YYYY" (Ex: 11/16/2023)
     * @param locator Attribute name
     */
    public void selectNextDate(String locator){
        //Construct next date from current date
        String formattedDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        //Constructing locator related to the given locator information
        By datePicker = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));

        //Select date from the date picker
        $(datePicker).setValue(formattedDate);
    }

    /**
     * Select given date from the date picker<br>
     * IMPORTANT! Date format should be "MM/DD/YYYY" (Ex: 2023-07-15)
     * @param date Date need to be selected
     * @param locator Locator of the element
     */
    public void selectGivenDate(String date, String locator){
        //Constructing locator related to the given locator information
        By datePicker = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));

        //Get text from dataSet.properties
        String textDate = getDataSetValue(date);

        //Select date from the
        $(datePicker).setValue(textDate);
    }

    /**
     * Upload file in given path to the file uploader
     * @param file File path
     * @param locator Element locator
     */
    public void uploadFile(String file, String locator){
        //Constructing locator related to the given locator information
        By fileUploader = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get file path from dataSet.properties
        String fileName = getDataSetValue(file);
        //Perform action
        $(fileUploader).uploadFile(new File(constantConfigs.testDataFolderPath+fileName));
    }

    /**
     * Clear information already in the field
     * @param locator Element locator
     */
    public void clearField(String locator){
        //Constructing locator related to the given locator information
        By field = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Perform action
        $(field).sendKeys(HOME, chord(SHIFT, END), BACK_SPACE);
    }

    /**
     * Fetch text from given field
     * @param locator Element locator
     * @return Return text in the given field
     */
    public String fetchTextFromField(String locator){
        //Constructing locator related to the given locator information
        By field = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Perform action
        return $(field).getValue();
    }

    /**
     * Get text from given element and fetch string value starts with given text
     * @param locator Element locator
     * @param startStr Starting string
     * @return Filtered string
     */
    public String fetchStringStartsWith(String locator,String startStr){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get text from dataSet.properties
        String startString = getDataSetValue(startStr);
        //Full string
        String fullString = $(element).getText();
        //Get substring start with given text
        int start = fullString.indexOf(startString);
        //Return text
        return fullString.substring(start);
    }

    /**
     * Fetch text from given element
      * @param locator Element locator
     * @return Return text in the given element
     */
    public String fetchTextFromElement(String locator){
        //Constructing locator related to the given locator information
        By field = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Perform action
        return $(field).getText();
    }

    /**
     * Fetch value from given field and compare length match with the given length
     * @param locator Element locator
     * @param length Expected field value length
     */
    public void fieldValueLength(String locator,String length){
        //Constructing locator related to the given locator information
        By field = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Check field value length
        Assertions.assertEquals(Integer.parseInt(length),Objects.requireNonNull($(field).getValue()).length(),"Field length does not match with the given length");
    }

    /**
     * Fetch value from given field, trim spaces between characters and compare length match with the given length
     * @param locator Element locator
     * @param length Expected field value length
     */
    public void fieldValueLengthWithoutSpaces(String locator,String length){
        //Constructing locator related to the given locator information
        By field = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Check field value length
        Assertions.assertEquals(Integer.parseInt(length),Objects.requireNonNull($(field).getValue()).replaceAll("\\s","").length(),"Field length does not match with the given length");
    }

    /**
     * Click provided element
     * @param locator Element locator need to be clicked
     */
    public void click(String locator){
        //Constructing locator related to the given locator information
        By button = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Perform action
        $(button).click();
    }

    /**
     * Click provided element if exist
     * @param locator Element locator need to be clicked
     */
    public void clickIfExist(String locator) {
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Click element if exist
        if($(element).exists()){
            //Perform action
            $(element).click();
        }
    }

    /**
     * Click on provided element after scrolling
     * @param locator Element locator need to be clicked
     */
    public void scrollAndClick(String locator){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Perform action
        $(element).scrollTo().click();
    }

    /**
     * Click child element within the provided element which match the given text.<br>
     * IMPORTANT! Case-sensitive
     * @param locator Element locator
     * @param text Text need to be match in the child element
     * @param scenario Current scenario object
     */
    public void clickMatchedText(String locator,String text,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get text from dataSet.properties
        String textValue = getDataSetValue(text);
        //Logging data to the cucumber report
        scenario.log(text+": "+textValue);
        //Perform action
        $$(element).findBy(exactText(textValue)).click();
    }

    /**
     * Click child element within the provided element which partially match the given text.<br>
     * IMPORTANT! Case-sensitive
     * @param locator Element locator
     * @param text Text need to be match in the child element
     * @param scenario Current scenario object
     */
    public void clickPartiallyMatchedText(String locator,String text,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get text from dataSet.properties
        String textValue = getDataSetValue(text);
        //Logging data to the cucumber report
        scenario.log(text+": "+textValue);
        //Perform action
        $$(element).findBy(text(textValue)).click();
    }

    /**
     * Click child element within the provided element which match the  exact given text.<br>
     * IMPORTANT! Case-sensitive
     * @param locator Element locator
     * @param text Text need to be match in the child element
     * @param scenario Current scenario object
     */
    public void clickMatchedExactText(String locator,String text,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Logging data to the cucumber report
        scenario.log("Clicked on: "+text);
        //Perform action
        $$(element).findBy(exactText(text)).click();
    }

    /**
     * Click child element within the provided element
     * @param locator Element locator
     * @param index Child element location
     * @param scenario Current scenario object
     */
    public void clickChildElement(String locator,String index,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get value from dataSet.properties
        int indexValue = Integer.parseInt(getDataSetValue(index));
        //Logging data to the cucumber report
        scenario.log("Position Clicked: "+indexValue);
        //Perform action
        //Deducted 1 to compensate index starting from 0
        $$(element).get(indexValue-1).click();
    }

    /**
     * Click toggle and change status to the given status
     * @param status Status toggle need to be set
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void toggleStatusUpdate(String status, String locator, Scenario scenario){
        //Constructing locator related to the given locator information
        By toggle = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));

        //Get desired status from dataSet.properties
        String statusText = getDataSetValue(status);

        //Logging data to the cucumber report
        scenario.log("Toggle Status: "+statusText);

        //Toggle based on current status
        if (statusText.equalsIgnoreCase("on") && !$(toggle).is(checked)) {
            $(toggle).click();
        } else if (statusText.equalsIgnoreCase("off") && $(toggle).is(checked)) {
            $(toggle).click();
        }
    }

    /**
     * Verify given text displayed on provided element.<br>
     * IMPORTANT! Case-sensitive
     * @param text Text need to be displayed
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void exactDisplaysOn(String text,String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get text from dataSet.properties
        String textValue = getDataSetValue(text);
        //Logging data to the cucumber report
        scenario.log(text+": "+textValue);
        //Perform action
        $(element).shouldHave(textCaseSensitive(textValue));
    }

    /**
     * Verify given exact value displayed on provided element (Ex - Input fields).<br>
     * IMPORTANT! Case-sensitive
     * @param text Text need to be displayed
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void exactValueDisplaysOn(String text,String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get text from dataSet.properties
        String textValue = getDataSetValue(text);
        //Logging data to the cucumber report
        scenario.log(text+": "+textValue);
        //Perform action
        $(element).shouldHave(exactValue(textValue));
    }

    /**
     * Verify given text displayed on provided element.<br>
     * IMPORTANT! Case-sensitive <br>
     * Usage - Used in the steps related to the remembering
     * @param text Text need to be displayed
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void exactTextDisplaysOn(String text, String locator, Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Logging data to the cucumber report
        scenario.log(text+": "+text);
        //Perform action
        $(element).shouldHave(textCaseSensitive(text));
    }

    /**
     * Verify given text displayed on provided element.<br>
     * IMPORTANT! Non-case-sensitive
     * @param text Text need to be displayed
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void displaysOn(String text,String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get text from dataSet.properties
        String textValue = getDataSetValue(text);
        //Logging data to the cucumber report
        scenario.log(text+": "+textValue);
        //Perform action
        $(element).shouldHave(text(textValue));
    }

    /**
     * Verify given value displayed on provided element (Ex - Input fields).<br>
     * IMPORTANT! Non-case-sensitive
     * @param text Text need to be displayed
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void valueDisplaysOn(String text,String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get text from dataSet.properties
        String textValue = getDataSetValue(text);
        //Logging data to the cucumber report
        scenario.log(text+": "+textValue);
        //Perform action
        $(element).shouldHave(value(textValue));
    }

    /**
     * Verify given text displayed on provided element.<br>
     * IMPORTANT! Non-case-sensitive <br>
     * Usage - Used in the steps related to the remembering
     * @param text Text need to be displayed
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void textDisplaysOn(String text, String locator, Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Logging data to the cucumber report
        scenario.log(text+": "+text);
        //Perform action
        $(element).shouldHave(text(text));
    }

    /**
     * Verify given element getting displayed
     * @param locator Element locator
     */
    public void gettingDisplayed(String locator){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Perform action
        $(element).shouldBe(visible);
    }

    /**
     * Verify user can interact with the given element
     * @param locator Element locator
     */
    public void enabledForInteractions(String locator){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Perform action
        $(element).isEnabled();
    }

    /**
     * Verify given element not displayed
     * @param locator Element locator
     */
    public void doNotDisplay(String locator){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Perform action
        $(element).shouldNot(visible);
    }

    /**
     * Wait until given element disappear
     * @param locator Element locator
     */
    public void waitUntilDisappear(String locator){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Perform action
        $(element).should(disappear);
    }

    /**
     * Scroll to the provided element
     * @param locator Element locator need to be scrolled
     */
    public void scrollTo(String locator){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Perform action
        $(element).scrollTo();
    }

    /**
     * Generate random name and enter into the element provided
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomName(String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get random name from faker
        String name = faker.name().fullName();
        //Logging data to the cucumber report
        scenario.log("Name: "+name);
        //Perform action
        $(element).val(name);
    }

    /**
     * Generate random first name and enter into the element provided
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomFirstName(String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get random first name from faker
        String firstName = faker.name().firstName();
        //Logging data to the cucumber report
        scenario.log("First Name: "+firstName);
        //Perform action
        $(element).val(firstName);
    }

    /**
     * Generate random last name and enter into the element provided
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomLastName(String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get random last name from faker
        String lastName = faker.name().lastName();
        //Logging data to the cucumber report
        scenario.log("Last Name: "+lastName);
        //Perform action
        $(element).val(lastName);
    }

    /**
     * Generate random username and enter into the element provided
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomUsername(String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get random username from faker
        String userName = faker.internet().username();
        //Logging data to the cucumber report
        scenario.log("Username: "+userName);
        //Perform action
        $(element).val(userName);
    }

    /**
     * Generate random company name and enter into the element provided
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomCompanyName(String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get random company name from faker
        String company = faker.company().name();
        //Logging data to the cucumber report
        scenario.log("Company Name: "+company);
        //Perform action
        $(element).val(company);
    }

    /**
     * Generate random city name and enter into the element provided
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomCityName(String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get random city name from faker
        String city = faker.address().cityName();
        //Logging data to the cucumber report
        scenario.log("City Name: "+city);
        //Perform action
        $(element).val(city);
    }

    /**
     * Generate random job title and enter into the element provided
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomJobTitle(String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get random job type from faker
        String job = faker.job().title();
        //Logging data to the cucumber report
        scenario.log("Job Title: "+job);
        //Perform action
        $(element).val(job);
    }

    /**
     * Generate random URL and enter into the element provided
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomURL(String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get random URL from faker
        String URL = faker.domain().fullDomain("automation");
        //Logging data to the cucumber report
        scenario.log("URL: "+URL);
        //Perform action
        $(element).val(URL);
    }

    /**
     * Generate random image and upload into the element provided
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomImage(String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By fileUploader = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get random image URL from faker
        String image = faker.internet().image();
        //Retrieving and saving image to a file
        try {
            //Retrieving image from the URL
            URL url = new URI(image).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream in = connection.getInputStream();
            OutputStream out = new FileOutputStream(constantConfigs.tempDataFolderPath+"//randomImage.jpg");
            byte[] buffer = new byte[4096];
            int n = -1;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            //Closing in/out streams
            out.close();
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while generating and saving random image refer stacktrace"+"\n"+e);
        }
        //Logging data to the cucumber report
        scenario.log("Image URL: "+image);
        //Perform action
        $(fileUploader).uploadFile(new File(constantConfigs.tempDataFolderPath+"//randomImage.jpg"));
    }

    /**
     * Generate random image with given dimension and upload into the element provided
     * @param dimension Image dimension
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomImage(String dimension,String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By fileUploader = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get dimension from dataSet.properties
        String[] imageDimension = getDataSetValue(dimension).split("x");
        //Get random image URL from faker
        String image = faker.internet().image(Integer.parseInt(imageDimension[0]),Integer.parseInt(imageDimension[1]));
        //Retrieving and saving image to a file
        try {
            //Retrieving image from the URL
            URL url = new URI(image).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream in = connection.getInputStream();
            OutputStream out = new FileOutputStream(constantConfigs.tempDataFolderPath+"//randomImage.jpg");
            byte[] buffer = new byte[4096];
            int n = -1;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            //Closing in/out streams
            out.close();
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while generating and saving random image refer stacktrace"+"\n"+e);
        }
        //Logging data to the cucumber report
        scenario.log("Image URL: "+image);
        //Perform action
        $(fileUploader).uploadFile(new File(constantConfigs.tempDataFolderPath+"//randomImage.jpg"));
    }

    /**
     * Generate random email and enter into the element provided
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomEmail(String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get random email from faker
        String email = faker.internet().emailAddress();
        //Logging data to the cucumber report
        scenario.log("Email: "+email);
        //Perform action
        $(element).val(email);
    }

    /**
     * Generate random phone number and enter into the element provided
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomPhoneNumber(String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get random phone number from faker
        String phone = faker.expression("#{numerify '077#######'}");
        //Logging data to the cucumber report
        scenario.log("Phone: "+phone);
        //Perform action
        $(element).val(phone);
    }

    /**
     * Generate random credit card number from given type and enter into the element provided
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomCreditCardNumber(String text,String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get text from dataSet.properties
        String textValue = getDataSetValue(text).toUpperCase();
        //Get random card number from faker
        String cardNumber;
        switch (textValue){
            case "VISA" -> {
                cardNumber = faker.finance().creditCard(Finance.CreditCardType.VISA);
            }
            case "MASTERCARD" -> {
                cardNumber = faker.finance().creditCard(Finance.CreditCardType.MASTERCARD);
            }
            case "AMERICAN EXPRESS" -> {
                cardNumber = faker.finance().creditCard(Finance.CreditCardType.AMERICAN_EXPRESS);
            }
            case "DINERS CLUB" -> {
                cardNumber = faker.finance().creditCard(Finance.CreditCardType.DINERS_CLUB);
            }
            case "DISCOVER" -> {
                cardNumber = faker.finance().creditCard(Finance.CreditCardType.DISCOVER);
            }
            default -> throw new RuntimeException("Given credit card type unknown");
        }
        //Logging data to the cucumber report
        scenario.log("Phone: "+cardNumber);
        //Perform action
        $(element).val(cardNumber);
    }

    /**
     * Generate random text and enter into the element provided
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomText(String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get random text from faker
        String text = faker.text().text();
        //Logging data to the cucumber report
        scenario.log("Text: "+text);
        //Perform action
        $(element).val(text);
    }

    /**
     * Generate random digits and enter into the element provided
     * @param count Number of digits need to be generated
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomDigits(String count,String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get value from dataSet.properties
        int digitCount = Integer.parseInt(getDataSetValue(count));
        //Get random digits from faker
        String digits = faker.number().digits(digitCount);
        //Logging data to the cucumber report
        scenario.log("Digits: "+digits);
        //Perform action
        $(element).val(digits);
    }

    /**
     * Generate random unique given number of digits and enter into the element provided
     * @param count Number of digits need to be generated
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomUniqueDigits(String count,String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get value from dataSet.properties
        int digitCount = Integer.parseInt(getDataSetValue(count));

        //Generate random digits
        Random random = new Random();
        int[] digits = random.ints(0,9).distinct().limit(digitCount).toArray();
        ArrayList<Integer> digitsList = new ArrayList<>(Arrays.stream(digits).boxed().toList());
        //Shuffle generated digits
        Collections.shuffle(digitsList,random);

        //Formatted Digits
        String formattedDigits = Arrays.toString(digitsList.toArray()).replace("[","").replace("]","").replace(",","").replace(" ","");

        //Logging data to the cucumber report
        scenario.log("Digits: "+ formattedDigits);
        //Perform action
        $(element).val(formattedDigits);
    }

    /**
     * Insert random generated digits with given text into the element provided
     * @param text Text need to be added with the digits
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomDigitsWithText(String text,String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get text from dataSet.properties
        String textValue = getDataSetValue(text);
        //Get random digits from faker
        String digits = faker.number().digits(3);
        //Logging data to the cucumber report
        scenario.log("Text with digits: "+textValue+"_"+digits);
        //Perform action
        $(element).val(textValue+"_"+digits);
    }

    /**
     * Generate random sentence and enter into the element provided
     * @param locator Element locator
     * @param scenario Current scenario object
     */
    public void insertRandomSentence(String locator,Scenario scenario){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Get random sentence from faker
        String sentence = faker.lorem().sentence();
        //Logging data to the cucumber report
        scenario.log("Sentence: "+sentence);
        //Perform action
        $(element).val(sentence);
    }

    /**
     * Click the given locator and capture the downloaded resource
     * <br> Downloaded resource resides on 'build/download/{latest session folder}
     * @param locator Locator need to be clicked
     * @return Path to the downloaded resource
     */
    public String download(String locator){
        //Constructing locator related to the given locator information
        By element = constructElement(findElementInRepo(constantConfigs.locatorsWorksheet,locator));
        //Perform download operation
        File downloadedFile = $(element).download(DownloadOptions.using(FileDownloadMode.FOLDER));
        //Return downloaded file path
        return downloadedFile.getPath();
    }

    /**
     * Switch to the newly opened tab otherwise switch to 1st tab
     */
    public void switchToNewTab(){
        //Switch to newly opened tab
        Selenide.switchTo().window(1);
    }

    /**
     * Switch to the given tab
     * @param tabIndex - Tab Index (Index start at 0 (Parent tab), from 1 onward other tabs)
     */
    public void switchToGivenTab(int tabIndex){
        //Switch to the tab in given index
        Selenide.switchTo().window(tabIndex);
    }

    /**
     * Switch to parent tab from other tabs
     */
    public void switchToParentTab(){
        //Switch to parent tab
        Selenide.switchTo().window(0);
    }

    /**
     * Switch to the given frame
     * @param frameIndex - Frame index
     */
    public void switchToGivenFrame(int frameIndex){
        //Switch to the frame in given index
        Selenide.switchTo().frame(frameIndex);
    }

    /**
     * Switch to the given frame identified by 'name' or 'id'
     * @param identifier - Frame 'name' or 'id'
     */
    public void switchToGivenFrame(String identifier){
        //Switch to the frame identified by 'name' or 'id'
        Selenide.switchTo().frame(identifier);
    }

    /**
     * Switch to parent frame from other frames
     */
    public void switchToParentFrame(){
        //Switch to parent frame
        Selenide.switchTo().defaultContent();
    }

    /**
     * Output ordinal numeral of the day
     * * <br>IMPORTANT! Not used by any step definition and only used by other methods in this class
     * @param day Day need which need to get ordinal numeral of
     * @return Ordinal numeral of the given day
     */
    private String getDayNumberSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        return switch (day % 10) {
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };
    }
}