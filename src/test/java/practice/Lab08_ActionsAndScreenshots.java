package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lab 08 - Actions API and Screenshots
 *
 * Learning Objectives:
 *   1. Use the Actions class for advanced user gestures
 *      - Mouse hover (moveToElement)
 *      - Double click
 *      - Right click (context click)
 *      - Click and hold / drag and drop
 *   2. Take a screenshot of the full page
 *   3. Take a screenshot of a specific element
 *
 * Background:
 *   Some interactions cannot be done with element.click() alone.
 *   The Actions class builds a chain of low-level input events
 *   (mouse move, key press, etc.) and executes them together.
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab08_ActionsAndScreenshots
 *
 * Test pages:
 *   Hover       : https://the-internet.herokuapp.com/hovers
 *   Drag & Drop : https://the-internet.herokuapp.com/drag_and_drop
 *   Context Menu: https://the-internet.herokuapp.com/context_menu
 */
public class Lab08_ActionsAndScreenshots {

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
    void mouseHover() {

        // ============================================================
        // STEP 1 — Create an Actions object
        //
        // Actions is a builder for composing complex user gestures.
        // Each method adds an action to the chain.
        // perform() executes the entire chain.
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/hovers");
        // Actions actions = new Actions(driver);

        // ============================================================
        // STEP 2 — Hover over an element
        //
        // moveToElement(element) moves the mouse pointer to the center
        // of the element, triggering CSS :hover and mouseover events.
        // ============================================================
        // WebElement avatar = driver.findElements(By.className("figure")).get(0);
        // actions.moveToElement(avatar).perform();

        // After hovering, a hidden caption appears
        // WebElement caption = avatar.findElement(By.className("figcaption"));
        // System.out.println("Hover caption: " + caption.getText());
        // assertTrue(caption.isDisplayed(), "Caption should appear on hover");
    }

    @Test
    void doubleClick() {

        // ============================================================
        // STEP 3 — Double click an element
        //
        // doubleClick(element) simulates two rapid clicks.
        // Used for text selection, inline editing, etc.
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com");
        // Actions actions = new Actions(driver);

        // WebElement link = driver.findElement(By.linkText("Checkboxes"));
        // actions.doubleClick(link).perform();

        // System.out.println("After double-click URL: " + driver.getCurrentUrl());
    }

    @Test
    void rightClick() {

        // ============================================================
        // STEP 4 — Right click (context click)
        //
        // contextClick(element) opens the browser context menu.
        // On the test page, a JS alert fires on right-click.
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/context_menu");
        // Actions actions = new Actions(driver);

        // WebElement box = driver.findElement(By.id("hot-spot"));
        // actions.contextClick(box).perform();

        // Handle the alert that appears after right-clicking
        // driver.switchTo().alert().accept();
        // System.out.println("Right-click context menu handled");
    }

    @Test
    void dragAndDrop() {

        // ============================================================
        // STEP 5 — Drag and drop
        //
        // dragAndDrop(source, target) performs a click-hold on source,
        // moves to target, then releases the mouse button.
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        // Actions actions = new Actions(driver);

        // WebElement columnA = driver.findElement(By.id("column-a"));
        // WebElement columnB = driver.findElement(By.id("column-b"));

        // System.out.println("Before drag — A: " + columnA.getText() + "  B: " + columnB.getText());

        // actions.dragAndDrop(columnA, columnB).perform();

        // System.out.println("After drag  — A: " + columnA.getText() + "  B: " + columnB.getText());
    }

    @Test
    void takeFullPageScreenshot() throws IOException {

        // ============================================================
        // STEP 6 — Capture a screenshot of the full browser viewport
        //
        // TakesScreenshot is implemented by ChromeDriver (and others).
        // getScreenshotAs(OutputType.FILE) returns a temporary File.
        // Copy it to a permanent location using Files.copy().
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com");

        // TakesScreenshot ts = (TakesScreenshot) driver;
        // File tempFile = ts.getScreenshotAs(OutputType.FILE);

        // Path destination = Paths.get("target", "screenshot-full.png");
        // Files.createDirectories(destination.getParent());
        // Files.copy(tempFile.toPath(), destination);

        // System.out.println("Screenshot saved to: " + destination.toAbsolutePath());
        // assertTrue(destination.toFile().exists(), "Screenshot file should exist");
    }

    @Test
    void takeElementScreenshot() throws IOException {

        // ============================================================
        // STEP 7 — Capture a screenshot of a specific element only
        //
        // WebElement also implements TakesScreenshot in Selenium 4.
        // The screenshot is cropped to the element's bounding box.
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/login");

        // WebElement loginForm = driver.findElement(By.id("login"));
        // File tempFile = loginForm.getScreenshotAs(OutputType.FILE);

        // Path destination = Paths.get("target", "screenshot-element.png");
        // Files.createDirectories(destination.getParent());
        // Files.copy(tempFile.toPath(), destination);

        // System.out.println("Element screenshot saved to: " + destination.toAbsolutePath());
        // assertTrue(destination.toFile().exists(), "Element screenshot file should exist");
    }
}
