package cucumber;

/**
 * This class hold the information that need to be shared between steps.
 * To grab the concept of state sharing between steps refer
 * "<a href="https://cucumber.io/docs/cucumber/state/?lang=java">Cucumber Docs</a>"
 */
public class World {
    String data;
    String tempFilePath;
}
