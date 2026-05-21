package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lab 03 - Element Interactions
 *
 * Learning Objectives:
 *   1. Click elements (buttons, links, checkboxes)
 *   2. Type text into input fields with sendKeys()
 *   3. Clear input fields with clear()
 *   4. Read element text and attributes
 *   5. Check element state: isDisplayed(), isEnabled(), isSelected()
 *   6. Find multiple elements with findElements()
 *   7. Use JUnit assertions to verify behavior
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab03_ElementInteractions
 *
 * Test pages:
 *   Login  : https://the-internet.herokuapp.com/login
 *   Checkbox: https://the-internet.herokuapp.com/checkboxes
 */
public class Lab03_ElementInteractions {

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
    void typeTextAndClick() {

        // ============================================================
        // STEP 1 — Type text into an input field
        //
        // sendKeys(text) simulates keyboard input into the element.
        // The element must be visible and interactable.
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/login");
        // WebElement username = driver.findElement(By.id("username"));
        // username.sendKeys("tomsmith");

        // ============================================================
        // STEP 2 — Clear an input field
        //
        // clear() removes all text from the field.
        // Useful when a field already has a value you need to replace.
        // ============================================================
        // username.clear();
        // username.sendKeys("tomsmith");

        // STEP 3 — Type into another field
        // WebElement password = driver.findElement(By.id("password"));
        // password.sendKeys("SuperSecretPassword!");

        // ============================================================
        // STEP 4 — Click a button
        //
        // click() simulates a mouse click on the element.
        // Use it on buttons, links, checkboxes, radio buttons, etc.
        // ============================================================
        // WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        // loginButton.click();

        // ============================================================
        // STEP 5 — Read the result text from a flash message
        //
        // getText() returns the visible inner text of an element.
        // ============================================================
        // WebElement flashMessage = driver.findElement(By.id("flash"));
        // System.out.println("Result: " + flashMessage.getText());
    }

    @Test
    void readTextAndAttributes() {

        // ============================================================
        // STEP 6 — Read text and attributes from elements
        //
        // getText()        — visible text between tags
        // getAttribute(name) — value of an HTML attribute
        //   e.g., getAttribute("href"), getAttribute("value"), getAttribute("class")
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/login");

        // WebElement usernameField = driver.findElement(By.id("username"));
        // System.out.println("Placeholder: " + usernameField.getAttribute("placeholder"));
        // System.out.println("Type attr:   " + usernameField.getAttribute("type"));

        // WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        // System.out.println("Button text: " + loginButton.getText());
        // System.out.println("Button class: " + loginButton.getAttribute("class"));
    }

    @Test
    void elementStateChecks() {

        // ============================================================
        // STEP 7 — Check the state of an element
        //
        // isDisplayed() — true if the element is visible on the page
        // isEnabled()   — true if the element can be interacted with
        // isSelected()  — true if a checkbox/radio button is checked
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/checkboxes");

        // List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        // WebElement checkbox1 = checkboxes.get(0);
        // WebElement checkbox2 = checkboxes.get(1);

        // System.out.println("Checkbox 1 displayed: " + checkbox1.isDisplayed());
        // System.out.println("Checkbox 1 selected:  " + checkbox1.isSelected());
        // System.out.println("Checkbox 2 selected:  " + checkbox2.isSelected());

        // ============================================================
        // STEP 8 — Click a checkbox to toggle it, then verify the state
        //
        // JUnit assertion: assertTrue(condition)
        // Fails the test if the condition is false — making it a real test.
        // ============================================================
        // checkbox1.click();
        // assertTrue(checkbox1.isSelected(), "Checkbox 1 should be checked after click");

        // checkbox2.click();
        // assertFalse(checkbox2.isSelected(), "Checkbox 2 should be unchecked after click");
    }

    @Test
    void findMultipleElements() {

        // ============================================================
        // STEP 9 — Find a list of elements with findElements()
        //
        // findElement()  — returns one WebElement, throws if not found
        // findElements() — returns List<WebElement>, returns empty list if none found
        //
        // Use findElements() when:
        //   - You expect multiple matching elements
        //   - You want to avoid an exception when the element might not exist
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com");

        // List<WebElement> links = driver.findElements(By.tagName("a"));
        // System.out.println("Total links on home page: " + links.size());

        // for (WebElement link : links) {
        //     System.out.println(" - " + link.getText() + " -> " + link.getAttribute("href"));
        // }

        // ============================================================
        // STEP 10 — Assert the number of elements found
        // ============================================================
        // assertFalse(links.isEmpty(), "There should be at least one link on the page");
        // assertTrue(links.size() > 10, "Home page should have more than 10 links");
    }
}
