package cucumber;

//Selenide, cucumber and JUnit imports
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SimpleReport;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

//Library Import
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

//Java Imports
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

//Custom imports
import base.webExecution;
import dataProviders.configFileReader;
import dataProviders.constantConfigs;

//Find the implementation of each step on 'webExecution' class
//located on 'java/base/webExecution'
public class WebSteps {

    //Initializing and local variables
    webExecution base = new webExecution();
    private final SimpleReport report = new SimpleReport();
    private Scenario scenario;

    //Dependency injection
    private World world;

    //Constructor
    public WebSteps(World world) {
        this.world = world;
    }

    // Runs before all scenarios
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @BeforeAll
    public static void beforeAll() {
        //Clear contains of the temp folders
        File screenshotDir = new File(constantConfigs.screenshotFolderPath);
        File downloadDir = new File(constantConfigs.downloadFolderPath);

        if (screenshotDir.exists() && downloadDir.exists()) {
            //Clean content of the folders if exist
            try {
                FileUtils.cleanDirectory(screenshotDir);
                FileUtils.cleanDirectory(downloadDir);
            } catch (IOException e) {
                throw new RuntimeException("Error occurred while cleaning temp folders refer stacktrace" + "\n" + e);
            }
        } else {
            //Create temp folders if not exist
            screenshotDir.mkdirs();
            downloadDir.mkdirs();
        }

        //Setting up configurations
        Configuration.baseUrl = configFileReader.getPropertyValue("baseUrl");
        Configuration.browser = configFileReader.getPropertyValue("browser");
        Configuration.headless = configFileReader.getBooleanPropertyValue("headless");
        Configuration.holdBrowserOpen = configFileReader.getBooleanPropertyValue("holdBrowserOpen");
        Configuration.timeout = configFileReader.getLongPropertyValue("timeout");

        //Set custom browser size
        if (!configFileReader.getPropertyValue("browserWindowSize").equals("Maximized") && !configFileReader.getPropertyValue("browserWindowSize").isEmpty())
            Configuration.browserSize = configFileReader.getPropertyValue("browserWindowSize");

        //Checking executing browser and setting related capabilities
        if (configFileReader.getPropertyValue("browser").equals("chrome")) {
            Configuration.browserCapabilities = configFileReader.getChromeBrowserCapabilities();
        } else if (configFileReader.getPropertyValue("browser").equals("firefox")) {
            Configuration.browserCapabilities = configFileReader.getFirefoxBrowserCapabilities();
        }
    }

    // Runs before each scenario
    @Before
    public void beforeTest(Scenario scenario) {
        //Simple text report generation to display on terminal
        report.start();
        //Logging executing environment
        scenario.log("Scenario executed on " + configFileReader.getPropertyValue("browser") + " browser");
        //Get current scenario
        this.scenario = scenario;
    }

    /*******Common actions*******/
    @Given("I have opened the webpage")
    public void iHaveOpenedTheWebpage() {
        //Opening baseURL configured in 'configs/configuration.properties'
        open("");
        //Make browser fullscreen if set
        if (configFileReader.getPropertyValue("browserWindowSize").equals("Maximized"))
            getWebDriver().manage().window().maximize();
    }

    @And("I see {string} enabled for interactions")
    public void iSeeEnabledForInteractions(String locator) {
        base.enabledForInteractions(locator);
    }

    /*******URL related action*******/
    @Given("I am navigating to {string} page")
    public void iAmNavigatingToPage(String URL) {
        base.openURL(URL, scenario);
    }

    /*******Browser tab/window related actions*******/
    @And("I switch to newly opened tab")
    public void iSwitchToNewlyOpenedTab() {
        base.switchToNewTab();
    }

    @And("I switch back to parent tab")
    public void iSwitchBackToParentTab() {
        base.switchToParentTab();
    }

    @And("I switch to {string} tab")
    public void iSwitchToTab(String tabIndex) {
        base.switchToGivenTab(Integer.parseInt(tabIndex));
    }

    /*******Browser frame/iframe related actions*******/
    @And("I switch to frame with {string} as name or id")
    public void iSwitchToFrameWithAsNameOrId(String identifier) {
        base.switchToGivenFrame(identifier);
    }

    @And("I switch to frame with {string} as index")
    public void iSwitchToFrameWithAsIndex(String index) {
        base.switchToGivenFrame(Integer.parseInt(index));
    }

    @And("I switch back to parent frame")
    public void iSwitchBackToParentFrame() {
        base.switchToParentFrame();
    }

    /*******Clicking related actions*******/
    @And("I click on {string}")
    public void iClickOn(String locator) {
        base.click(locator);
    }

    @And("I click on {string} if exist")
    public void iClickOnIfExist(String locator) {
        base.clickIfExist(locator);
    }

    @And("I click button {string}")
    public void iClickButton(String locator) {
        base.click(locator);
    }

    @When("I click link {string}")
    public void iClickLink(String locator) {
        base.click(locator);
    }

    @When("I click the tab {string}")
    public void iClickTheTab(String locator) {
        base.click(locator);
    }

    @And("I click checkbox {string}")
    public void iClickCheckbox(String locator) {
        base.click(locator);
    }

    @And("I click radio button {string}")
    public void iClickRadioButton(String locator) {
        base.click(locator);
    }

    @And("I expand list by clicking {string}")
    public void iExpandListByClicking(String locator) {
        base.click(locator);
    }

    @And("I click on {string} after scrolling")
    public void iClickOnAfterScrolling(String locator) {
        base.scrollAndClick(locator);
    }

    @Then("I click on {string} with text {string}")
    public void i_click_on_with_text(String locator, String text) {
        base.clickMatchedText(locator, text, scenario);
    }

    @And("I click on {string} with partial text {string}")
    public void iClickOnWithPartialText(String locator, String text) {
        base.clickPartiallyMatchedText(locator, text, scenario);
    }

    @And("I click on child element at {string} position from {string}")
    public void iClickOnChildElementAtPositionFrom(String index, String locator) {
        base.clickChildElement(locator, index, scenario);
    }

    @And("I turn {string} toggle displayed at {string}")
    public void iTurnToggleDisplayedAt(String status, String locator) {
        base.toggleStatusUpdate(status, locator, scenario);
    }

    /*******Field related actions*******/
    @When("I type {string} in {string} textarea")
    public void iTypeInTextarea(String text, String locator) {
        base.typeText(text, locator, scenario);
    }

    @And("I type {string} in {string} textarea and search")
    public void iTypeInTextareaAndSearch(String text, String locator) {
        base.typeTextAndSearch(text, locator, scenario);
    }

    @And("I type {string} in {string} dropdown area")
    public void iTypeInDropdownArea(String text, String locator) {
        base.typeTextToDropdown(text, locator, scenario);
    }

    @And("I select {string} from {string} dropdown")
    public void iSelectFromDropdown(String text, String locator) {
        base.selectTextFromDropdown(text, locator, scenario);
    }

    @And("I type {string} then wait until {string} disappear and select from {string} dropdown")
    public void iTypeThenWaitUntilDisappearAndSelectFromDropdown(String text, String locator1, String locator2) {
        base.searchTextFromDropdown(text, locator1, locator2, scenario);
    }

    @And("I select current date from the {string}")
    public void iSelectCurrentDateFromThe(String locator) {
        base.selectCurrentDate(locator);
    }

    @And("I select next day from the {string}")
    public void iSelectNextDayFromThe(String locator) {
        base.selectNextDate(locator);
    }

    @And("I select {string} day from the {string}")
    public void iSelectDayFromThe(String date, String locator) {
        base.selectGivenDate(date, locator);
    }

    @And("I upload {string} to field {string}")
    public void iUploadToField(String filePath, String locator) {
        base.uploadFile(filePath, locator);
    }

    @And("I clear the field {string}")
    public void iClearTheField(String locator) {
        base.clearField(locator);
    }

    @And("I check {string} field value length equals to {string}")
    public void iCheckFieldValueLengthEqualsTo(String locator, String length) {
        base.fieldValueLength(locator, length);
    }

    @Then("I check {string} field value length without spaces equals to {string}")
    public void iCheckFieldValueLengthWithoutSpacesEqualsTo(String locator, String length) {
        base.fieldValueLengthWithoutSpaces(locator, length);
    }

    /*******Random data generation related actions*******/
    @And("I enter random name to the {string} field")
    public void iEnterRandomNameToTheField(String locator) {
        base.insertRandomName(locator, scenario);
    }

    @And("I enter random first name to the {string} field")
    public void iEnterRandomFirstNameToTheField(String locator) {
        base.insertRandomFirstName(locator, scenario);
    }

    @And("I enter random last name to the {string} field")
    public void iEnterRandomLastNameToTheField(String locator) {
        base.insertRandomLastName(locator, scenario);
    }

    @And("I enter random username to the {string} field")
    public void iEnterRandomUsernameToTheField(String locator) {
        base.insertRandomUsername(locator, scenario);
    }

    @And("I enter random company name to the {string} field")
    public void iEnterRandomCompanyNameToTheField(String locator) {
        base.insertRandomCompanyName(locator, scenario);
    }

    @And("I enter random city name to the {string}")
    public void iEnterRandomCityNameToThe(String locator) {
        base.insertRandomCityName(locator, scenario);
    }

    @And("I enter random job title to the {string} field")
    public void iEnterRandomJobTitleToTheField(String locator) {
        base.insertRandomJobTitle(locator, scenario);
    }

    @And("I enter random URL to the {string} field")
    public void iEnterRandomURLToTheField(String locator) {
        base.insertRandomURL(locator, scenario);
    }

    @And("I upload random image to the {string} field")
    public void iUploadRandomImageToTheField(String locator) {
        base.insertRandomImage(locator, scenario);
    }

    @And("I upload random image to the {string} field with dimension of {string}")
    public void iUploadRandomImageToTheFieldWithDimensionOf(String locator, String dimension) {
        base.insertRandomImage(dimension, locator, scenario);
    }

    @And("I enter random email to the {string} field")
    public void iEnterRandomEmailToTheField(String locator) {
        base.insertRandomEmail(locator, scenario);
    }

    @And("I enter random phone number to the {string} field")
    public void iEnterRandomPhoneNumberToTheField(String locator) {
        base.insertRandomPhoneNumber(locator, scenario);
    }

    @And("I enter random {string} type card number to the {string}")
    public void iEnterRandomTypeCardNumberToThe(String text, String locator) {
        base.insertRandomCreditCardNumber(text,locator,scenario);
    }

    @And("I enter random {string} digits to the {string} field")
    public void iEnterRandomDigitsToTheField(String count, String locator) {
        base.insertRandomDigits(count, locator, scenario);
    }

    @And("I enter random unique {string} digits to the {string} field")
    public void iEnterRandomUniqueDigitsToTheField(String count, String locator) {
        base.insertRandomUniqueDigits(count, locator, scenario);
    }

    @When("I enter {string} with random digits to the {string} field")
    public void iEnterWithRandomDigitsToTheField(String text, String locator) {
        base.insertRandomDigitsWithText(text, locator, scenario);
    }

    @And("I enter random sentence to the {string} field")
    public void iEnterRandomSentenceToTheField(String locator) {
        base.insertRandomSentence(locator, scenario);
    }

    @And("I enter random text to the {string} field")
    public void iEnterRandomTextToTheField(String locator) {
        base.insertRandomText(locator,scenario);
    }

    /*******Visibility related actions*******/
    @Then("I see the exact {string} displays on {string}")
    public void iSeeTheExactDisplaysOn(String text, String locator) {
        base.exactDisplaysOn(text, locator, scenario);
    }

    @And("I see the exact value {string} displays on {string}")
    public void iSeeTheExactValueDisplaysOn(String text, String locator) {
        base.exactValueDisplaysOn(text, locator, scenario);
    }

    @Then("I see the {string} displays on {string}")
    public void iSeeTheDisplaysOn(String text, String locator) {
        base.displaysOn(text, locator, scenario);
    }

    @And("I see the value {string} displays on {string}")
    public void iSeeTheValueDisplaysOn(String text, String locator) {
        base.valueDisplaysOn(text, locator, scenario);
    }

    @Then("I see {string} getting displayed")
    public void iSeeGettingDisplayed(String locator) {
        base.gettingDisplayed(locator);
    }

    @Given("I do not see {string}")
    public void iDoNotSee(String locator) {
        base.doNotDisplay(locator);
    }

    @Given("I wait until {string} disappear")
    public void iWaitUntilDisappear(String locator) {
        base.waitUntilDisappear(locator);
    }

    @And("I scroll to {string}")
    public void iScrollTo(String locator) {
        base.scrollTo(locator);
    }

    /*******State sharing related actions*******/
    @And("I remember value in {string} field")
    public void iRememberValueInField(String locator) {
        world.data = base.fetchTextFromField(locator);
    }

    @And("I remember value in {string} element")
    public void iRememberValueInElement(String locator) {
        world.data = base.fetchTextFromElement(locator);
    }

    @And("I remember value in {string} element starting with {string}")
    public void iRememberValueInElementStartingWith(String locator, String startStr) {
        world.data = base.fetchStringStartsWith(locator, startStr);
    }

    @And("I type remembered value to the {string}")
    public void iTypeRememberedValueToThe(String locator) {
        base.typeExactText(world.data, locator, scenario);
    }

    @And("I click on remembered value displays on {string}")
    public void iClickOnRememberedValueDisplaysOn(String locator) {
        base.clickMatchedExactText(locator, world.data, scenario);
    }

    @And("I see the remembered value displays on {string}")
    public void iSeeTheRememberedValueDisplaysOn(String locator) {
        base.textDisplaysOn(world.data, locator, scenario);
    }

    @Then("I see the exact remembered value displays on {string}")
    public void iSeeTheExactRememberedValueDisplaysOn(String locator) {
        base.exactTextDisplaysOn(world.data, locator, scenario);
    }

    /*******Dataset related actions*******/
    @And("I retrieve value in {string} and update dataset value with {string} key")
    public void iRetrieveValueInAndUpdateDatasetValueWithKey(String locator, String key) {
        base.updateDataset(locator, key);
    }

    /*******Download related actions*******/
    @And("I click on {string} and download file")
    public void iClickOnAndDownloadFile(String locator) {
        world.tempFilePath = base.download(locator);
    }

    @And("I check downloaded file extension is {string}")
    public void iCheckDownloadedFileExtensionIs(String extension) {
        Assertions.assertEquals(extension, FilenameUtils.getExtension(world.tempFilePath), "Downloaded file extension does not match with the '" + extension + "' extension");
    }

    @And("I check downloaded file name contains {string}")
    public void iCheckDownloadedFileNameContains(String text) {
        String fileName = FilenameUtils.getBaseName(world.tempFilePath);
        Assertions.assertTrue(fileName.contains(text), "Downloaded file name (" + fileName + ") does not contains '" + text + "' text");
    }

    @And("I check downloaded file name is {string}")
    public void iCheckDownloadedFileNameIs(String fileName) {
        Assertions.assertEquals(fileName, FilenameUtils.getName(world.tempFilePath), "Downloaded file name does not match with '" + fileName + "' text");
    }

    // Runs after each scenario
    @After
    public void afterScenario(Scenario scenario) throws IOException {
        //Simple text report generation to display on terminal
        report.finish(scenario.getName());

        //Clearing browser cookies
        Selenide.clearBrowserCookies();

        //Closing browser
        Selenide.closeWindow();

        //Attaching last screenshot if scenario failed to execute
        if (scenario.isFailed()) {
            //Retrieving last screenshot from file system (Path://build/reports/tests)
            File lastScreenshot = Screenshots.getLastScreenshot();
            //Verifying screenshot availability
            if (lastScreenshot != null) {
                //Converting screenshot to byte
                byte[] bytes = Files.readAllBytes(lastScreenshot.toPath());
                //Attaching screenshot
                scenario.attach(bytes, "image/png", "Failed step screenshot");
            }
        }
    }
}