package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Lab 02 - Element Locators
 *
 * Learning Objectives:
 *   1. Understand what a locator is and why it matters
 *   2. Use all major locator strategies: id, name, className, tagName,
 *      linkText, partialLinkText, cssSelector, xpath
 *   3. Understand when to prefer one locator over another
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab02_Locators
 *
 * Test page: https://the-internet.herokuapp.com/login
 *   username field: id="username", name="username"
 *   password field: id="password", name="password"
 *   submit button:  class="radius", type="submit"
 */
public class Lab02_Locators {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        // Uncomment after completing Lab 01
        // WebDriverManager.chromedriver().setup();
        // driver = new ChromeDriver();
        // driver.get("https://the-internet.herokuapp.com/login");
    }

    @AfterEach
    void tearDown() {
        // if (driver != null) driver.quit();
    }

    @Test
    void locateById() {

        // ============================================================
        // STEP 1 — By.id
        //
        // The fastest and most reliable locator.
        // Use it whenever an element has a unique id attribute.
        // HTML: <input id="username" ... />
        // ============================================================
        // WebElement usernameField = driver.findElement(By.id("username"));
        // System.out.println("Found by id: " + usernameField.getTagName());
    }

    @Test
    void locateByName() {

        // ============================================================
        // STEP 2 — By.name
        //
        // Finds elements by the name attribute.
        // Common for form fields where name is used for form submission.
        // HTML: <input name="password" ... />
        // ============================================================
        // WebElement passwordField = driver.findElement(By.name("password"));
        // System.out.println("Found by name: " + passwordField.getTagName());
    }

    @Test
    void locateByClassName() {

        // ============================================================
        // STEP 3 — By.className
        //
        // Finds elements by a single CSS class name.
        // If an element has multiple classes, use just one of them.
        // HTML: <button class="radius" ... />
        // Note: By.className does NOT support compound selectors (e.g. "foo bar")
        // ============================================================
        // WebElement loginButton = driver.findElement(By.className("radius"));
        // System.out.println("Found by className: " + loginButton.getText());
    }

    @Test
    void locateByTagName() {

        // ============================================================
        // STEP 4 — By.tagName
        //
        // Finds elements by their HTML tag (e.g., "input", "a", "button").
        // Usually returns multiple elements — use findElements() for lists.
        // ============================================================
        // java.util.List<WebElement> inputs = driver.findElements(By.tagName("input"));
        // System.out.println("Number of <input> elements: " + inputs.size());
    }

    @Test
    void locateByLinkText() {

        // ============================================================
        // STEP 5 — By.linkText / By.partialLinkText
        //
        // Finds <a> anchor elements by their visible text.
        // linkText        — must match the full link text exactly
        // partialLinkText — matches if the link text contains the given string
        //
        // Navigate to the home page first (it has links to all sub-pages)
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com");

        // Full text match:
        // WebElement link = driver.findElement(By.linkText("Form Authentication"));
        // System.out.println("Found by linkText: " + link.getText());

        // Partial text match:
        // WebElement partialLink = driver.findElement(By.partialLinkText("Authentication"));
        // System.out.println("Found by partialLinkText: " + partialLink.getText());
    }

    @Test
    void locateByCssSelector() {

        // ============================================================
        // STEP 6 — By.cssSelector
        //
        // Uses CSS selector syntax — flexible and widely used.
        // More readable than XPath for most cases.
        //
        // Common patterns:
        //   #id             → by id
        //   .className      → by class
        //   tag             → by tag name
        //   tag#id          → tag with id
        //   tag.class       → tag with class
        //   [attribute]     → has attribute
        //   [attr='value']  → attribute equals value
        //   parent > child  → direct child
        //   ancestor desc   → descendant
        // ============================================================
        // WebElement byId     = driver.findElement(By.cssSelector("#username"));
        // WebElement byClass  = driver.findElement(By.cssSelector(".radius"));
        // WebElement byAttr   = driver.findElement(By.cssSelector("input[type='text']"));
        // WebElement nested   = driver.findElement(By.cssSelector("form#login input[name='username']"));

        // System.out.println("CSS #username tag: " + byId.getTagName());
        // System.out.println("CSS .radius text: " + byClass.getText());
    }

    @Test
    void locateByXPath() {

        // ============================================================
        // STEP 7 — By.xpath
        //
        // XPath (XML Path Language) can navigate the full DOM tree.
        // More powerful than CSS selectors but harder to read.
        // Prefer CSS selectors unless you need XPath-specific features.
        //
        // Common patterns:
        //   //tag                     → any <tag> anywhere in the document
        //   //tag[@attr='value']      → tag with attribute value
        //   //tag[text()='...']       → tag with exact text content
        //   //tag[contains(@attr,'v')]→ attribute contains value
        //   //tag[contains(text(),'v')]→ text contains value
        //   //parent/child            → direct child
        //   //tag/..                  → parent of tag
        // ============================================================
        // WebElement byAttr   = driver.findElement(By.xpath("//input[@id='username']"));
        // WebElement byText   = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
        // WebElement relative = driver.findElement(By.xpath("//form[@id='login']//input[@name='password']"));

        // System.out.println("XPath by attr: " + byAttr.getTagName());
        // System.out.println("XPath by text: " + byText.getText());
    }
}
