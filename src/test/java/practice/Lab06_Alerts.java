package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lab 06 - JavaScript Alerts
 *
 * Learning Objectives:
 *   1. Understand the three types of JS dialogs: alert, confirm, prompt
 *   2. Switch focus to the dialog using switchTo().alert()
 *   3. Accept (OK) or dismiss (Cancel) the dialog
 *   4. Read the dialog message text
 *   5. Type into a prompt dialog with sendKeys()
 *
 * Background:
 *   JavaScript dialogs (alert, confirm, prompt) are browser-level pop-ups,
 *   NOT HTML elements in the page DOM. You cannot use findElement() to access them.
 *   You must use driver.switchTo().alert() to get an Alert object.
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab06_Alerts
 *
 * Test page: https://the-internet.herokuapp.com/javascript_alerts
 */
public class Lab06_Alerts {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        // WebDriverManager.chromedriver().setup();
        // driver = new ChromeDriver();
        // driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @AfterEach
    void tearDown() {
        // if (driver != null) driver.quit();
    }

    @Test
    void handleSimpleAlert() {

        // ============================================================
        // STEP 1 — Trigger and accept a simple alert
        //
        // A simple alert() has only an OK button.
        // accept() clicks OK and returns focus to the page.
        // ============================================================
        // driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        // ============================================================
        // STEP 2 — Switch to the alert and interact with it
        //
        // switchTo().alert() returns an Alert object.
        // If no alert is present, this throws NoAlertPresentException.
        // Use WebDriverWait to ensure the alert is present before switching.
        // ============================================================
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // Read the alert message
        // System.out.println("Alert message: " + alert.getText());

        // Click OK
        // alert.accept();

        // Verify the result message on the page
        // String result = driver.findElement(By.id("result")).getText();
        // assertEquals("You successfully clicked an alert", result);
    }

    @Test
    void handleConfirmAlert_accept() {

        // ============================================================
        // STEP 3 — Accept a confirm dialog (click OK)
        //
        // A confirm() dialog has both OK and Cancel.
        // accept()  → clicks OK   (user confirmed)
        // dismiss() → clicks Cancel (user cancelled)
        // ============================================================
        // driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Alert confirm = wait.until(ExpectedConditions.alertIsPresent());

        // System.out.println("Confirm message: " + confirm.getText());
        // confirm.accept(); // click OK

        // String result = driver.findElement(By.id("result")).getText();
        // assertEquals("You clicked: Ok", result);
    }

    @Test
    void handleConfirmAlert_dismiss() {

        // ============================================================
        // STEP 4 — Dismiss a confirm dialog (click Cancel)
        // ============================================================
        // driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Alert confirm = wait.until(ExpectedConditions.alertIsPresent());

        // confirm.dismiss(); // click Cancel

        // String result = driver.findElement(By.id("result")).getText();
        // assertEquals("You clicked: Cancel", result);
    }

    @Test
    void handlePromptAlert() {

        // ============================================================
        // STEP 5 — Type into a prompt dialog and accept it
        //
        // A prompt() dialog has a text input field.
        // sendKeys(text) types into the prompt's input field.
        // Then call accept() to submit, or dismiss() to cancel.
        // ============================================================
        // driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Alert prompt = wait.until(ExpectedConditions.alertIsPresent());

        // System.out.println("Prompt message: " + prompt.getText());
        // prompt.sendKeys("Hello Selenium!");
        // prompt.accept();

        // String result = driver.findElement(By.id("result")).getText();
        // assertEquals("You entered: Hello Selenium!", result);
    }
}
