package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

/**
 * Lab 04 - Waits
 *
 * Learning Objectives:
 *   1. Understand why waits are necessary in web automation
 *   2. Know why Thread.sleep() is a bad practice
 *   3. Use Implicit Wait — global timeout for finding elements
 *   4. Use Explicit Wait (WebDriverWait) — wait for a specific condition
 *   5. Use Fluent Wait — explicit wait with polling interval and ignored exceptions
 *
 * Why Waits Matter:
 *   Modern web pages load content dynamically (AJAX, animations, lazy loading).
 *   If Selenium tries to interact with an element before it appears, the test fails.
 *   Waits tell Selenium to be patient and retry until the element is ready.
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab04_Waits
 *
 * Test page: https://the-internet.herokuapp.com/dynamic_loading/1
 *   This page shows a hidden element that appears only after clicking "Start"
 *   and waiting ~5 seconds for a loading bar to complete.
 */
public class Lab04_Waits {

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
    void badPractice_threadSleep() {

        // ============================================================
        // STEP 1 — Thread.sleep() (DO NOT USE in real tests)
        //
        // Thread.sleep(ms) pauses the entire thread for a fixed time.
        // Problems:
        //   - Wastes time if the element appears early
        //   - Still fails if the page is slower than expected
        //   - Not synchronized with the actual page state
        // This step exists only to show what NOT to do.
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        // driver.findElement(By.cssSelector("#start button")).click();

        // try {
        //     Thread.sleep(6000); // blindly wait 6 seconds — bad practice
        // } catch (InterruptedException e) {
        //     Thread.currentThread().interrupt();
        // }

        // WebElement result = driver.findElement(By.id("finish"));
        // System.out.println("Result text: " + result.getText());
    }

    @Test
    void implicitWait() {

        // ============================================================
        // STEP 2 — Implicit Wait
        //
        // Sets a global timeout for ALL findElement() calls.
        // If the element is not immediately found, Selenium will keep
        // retrying for up to the specified duration before throwing.
        //
        // Set once in setUp() — applies to every element lookup.
        // Scope: global (all findElement calls in this driver session)
        //
        // Limitation: applies only to "element not found" — it does NOT
        // wait for elements to become visible, clickable, etc.
        // ============================================================
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        // driver.findElement(By.cssSelector("#start button")).click();

        // The element exists in DOM but is hidden — implicit wait won't help here
        // driver.findElement(By.id("finish")); // this may still fail — see STEP 3
    }

    @Test
    void explicitWait() {

        // ============================================================
        // STEP 3 — Explicit Wait with WebDriverWait (RECOMMENDED)
        //
        // Waits for a specific CONDITION to become true, not just for
        // the element to appear in the DOM.
        //
        // WebDriverWait polls the condition every 500ms (by default)
        // until it returns a non-null/non-false value or times out.
        //
        // Common ExpectedConditions:
        //   visibilityOfElementLocated(By)  — element is visible
        //   elementToBeClickable(By)        — element is visible AND enabled
        //   presenceOfElementLocated(By)    — element exists in DOM (not necessarily visible)
        //   textToBe(By, text)              — element's text equals the given string
        //   titleContains(text)             — page title contains string
        //   alertIsPresent()                — a JS alert dialog is open
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        // driver.findElement(By.cssSelector("#start button")).click();

        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // WebElement finish = wait.until(
        //     ExpectedConditions.visibilityOfElementLocated(By.id("finish"))
        // );
        // System.out.println("Result: " + finish.getText());
    }

    @Test
    void fluentWait() {

        // ============================================================
        // STEP 4 — Fluent Wait
        //
        // A more configurable version of WebDriverWait.
        // Lets you set:
        //   withTimeout()        — maximum wait duration
        //   pollingEvery()       — how often to check the condition
        //   ignoring()           — which exceptions to suppress while waiting
        //
        // Use FluentWait when you need fine-grained control over polling,
        // or when you want to ignore specific exceptions during the wait.
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        // driver.findElement(By.cssSelector("#start button")).click();

        // FluentWait<WebDriver> wait = new FluentWait<>(driver)
        //     .withTimeout(Duration.ofSeconds(15))     // max total wait
        //     .pollingEvery(Duration.ofMillis(500))    // check every 500ms
        //     .ignoring(NoSuchElementException.class); // don't fail if not found yet

        // WebElement finish = wait.until(d -> {
        //     WebElement el = d.findElement(By.id("finish"));
        //     return el.isDisplayed() ? el : null; // return non-null to stop waiting
        // });
        // System.out.println("Result: " + finish.getText());
    }
}
