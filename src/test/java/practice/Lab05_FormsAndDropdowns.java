package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lab 05 - Forms and Dropdowns
 *
 * Learning Objectives:
 *   1. Fill out and submit an HTML form
 *   2. Use the Select class to interact with <select> dropdowns
 *   3. Select options by visible text, value attribute, and index
 *   4. Read all available options from a dropdown
 *   5. Verify the selected option with assertions
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab05_FormsAndDropdowns
 *
 * Test pages:
 *   Login form : https://the-internet.herokuapp.com/login
 *   Dropdown   : https://the-internet.herokuapp.com/dropdown
 */
public class Lab05_FormsAndDropdowns {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        // WebDriverManager.chromedriver().setup();
        // driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        // if (driver != null) driver.quit();
    }

    @Test
    void submitLoginForm() {

        // ============================================================
        // STEP 1 — Fill in the login form and submit
        //
        // Standard form workflow:
        //   1. Locate each input field
        //   2. Type a value with sendKeys()
        //   3. Click the submit button (or call submit() on the form/input)
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/login");

        // driver.findElement(By.id("username")).sendKeys("tomsmith");
        // driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        // ============================================================
        // STEP 2 — Submit the form
        //
        // Option A: click the submit button
        // Option B: call .submit() on any element inside the form
        //           (Selenium sends a form submit event)
        // ============================================================
        // driver.findElement(By.cssSelector("button[type='submit']")).click();
        // -- OR --
        // driver.findElement(By.id("username")).submit();

        // ============================================================
        // STEP 3 — Verify the result using a JUnit assertion
        //
        // assertEquals(expected, actual, message)
        //   Fails the test if expected != actual
        // assertTrue(condition, message)
        //   Fails the test if the condition is false
        // ============================================================
        // String flash = driver.findElement(By.id("flash")).getText();
        // assertTrue(flash.contains("You logged into a secure area!"),
        //     "Expected success message but got: " + flash);

        // Verify we landed on the secure page
        // assertEquals("https://the-internet.herokuapp.com/secure",
        //     driver.getCurrentUrl(),
        //     "Should redirect to /secure after login");
    }

    @Test
    void selectDropdownByVisibleText() {

        // ============================================================
        // STEP 4 — Create a Select object
        //
        // The Select class wraps a <select> element and provides
        // helper methods for choosing options.
        // You must pass the <select> element itself, not an <option>.
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/dropdown");
        // WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        // Select dropdown = new Select(dropdownElement);

        // ============================================================
        // STEP 5 — Select by visible text
        //
        // selectByVisibleText(text) — clicks the option whose visible
        // label matches the given string exactly.
        // HTML: <option value="1">Option 1</option>
        // ============================================================
        // dropdown.selectByVisibleText("Option 1");
        // System.out.println("Selected: " + dropdown.getFirstSelectedOption().getText());
    }

    @Test
    void selectDropdownByValue() {

        // ============================================================
        // STEP 6 — Select by value attribute
        //
        // selectByValue(value) — matches the value="" attribute of <option>.
        // HTML: <option value="2">Option 2</option>
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/dropdown");
        // Select dropdown = new Select(driver.findElement(By.id("dropdown")));

        // dropdown.selectByValue("2");
        // assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
    }

    @Test
    void selectDropdownByIndex() {

        // ============================================================
        // STEP 7 — Select by index (0-based)
        //
        // selectByIndex(n) — selects the nth <option> in the list.
        // Index 0 is the first option (often a placeholder like "-- Select --").
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/dropdown");
        // Select dropdown = new Select(driver.findElement(By.id("dropdown")));

        // dropdown.selectByIndex(1); // selects "Option 1"
        // System.out.println("Selected at index 1: " + dropdown.getFirstSelectedOption().getText());
    }

    @Test
    void getAllOptions() {

        // ============================================================
        // STEP 8 — Read all available options from the dropdown
        //
        // getOptions()             — all <option> elements in the list
        // getFirstSelectedOption() — the currently selected option
        // getAllSelectedOptions()  — for multi-select dropdowns
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/dropdown");
        // Select dropdown = new Select(driver.findElement(By.id("dropdown")));

        // List<WebElement> options = dropdown.getOptions();
        // System.out.println("All options:");
        // for (WebElement option : options) {
        //     System.out.println("  text='" + option.getText()
        //         + "'  value='" + option.getAttribute("value") + "'");
        // }

        // assertEquals(3, options.size(), "Dropdown should have 3 options");
    }
}
