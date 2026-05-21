package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lab 07 - Multiple Windows and Frames
 *
 * Learning Objectives:
 *   1. Understand browser window handles
 *   2. Switch between multiple browser windows/tabs
 *   3. Switch into an iframe to interact with its content
 *   4. Switch back to the main page after leaving a frame
 *
 * Background:
 *   When a page opens a new window/tab, or embeds content in an <iframe>,
 *   Selenium's focus stays on the original context.
 *   You must explicitly switch context to interact with the new window or frame.
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Run: mvn test -Dtest=Lab07_WindowsAndFrames
 *
 * Test pages:
 *   Multiple windows: https://the-internet.herokuapp.com/windows
 *   Frames          : https://the-internet.herokuapp.com/iframe
 */
public class Lab07_WindowsAndFrames {

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
    void switchBetweenWindows() {

        // ============================================================
        // STEP 1 — Get the handle of the current (original) window
        //
        // A window handle is a unique string ID for each open browser window/tab.
        // getWindowHandle()  — returns the handle of the currently focused window
        // getWindowHandles() — returns a Set of all open window handles
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/windows");
        // String originalWindow = driver.getWindowHandle();
        // System.out.println("Original window handle: " + originalWindow);

        // ============================================================
        // STEP 2 — Click a link that opens a new window
        // ============================================================
        // driver.findElement(By.linkText("Click Here")).click();

        // ============================================================
        // STEP 3 — Switch focus to the new window
        //
        // After clicking, the new window exists but Selenium still controls
        // the original window. We must switch to the new handle.
        // ============================================================
        // Set<String> allHandles = driver.getWindowHandles();
        // System.out.println("Total windows open: " + allHandles.size());

        // for (String handle : allHandles) {
        //     if (!handle.equals(originalWindow)) {
        //         driver.switchTo().window(handle); // switch focus to new window
        //         break;
        //     }
        // }

        // ============================================================
        // STEP 4 — Interact with the new window
        // ============================================================
        // System.out.println("New window title: " + driver.getTitle());
        // System.out.println("New window URL:   " + driver.getCurrentUrl());

        // ============================================================
        // STEP 5 — Switch back to the original window
        // ============================================================
        // driver.switchTo().window(originalWindow);
        // System.out.println("Back to: " + driver.getTitle());
    }

    @Test
    void switchIntoIframe() {

        // ============================================================
        // STEP 6 — Switch into an iframe
        //
        // An <iframe> is an embedded page within a page.
        // Elements inside the iframe are NOT accessible until you switch into it.
        //
        // Three ways to switch into a frame:
        //   switchTo().frame(index)      — by position (0-based)
        //   switchTo().frame("name/id")  — by name or id attribute
        //   switchTo().frame(WebElement) — by the <iframe> element itself (most reliable)
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com/iframe");

        // Find the iframe element first, then switch into it
        // WebElement iframeElement = driver.findElement(By.id("mce_0_ifr"));
        // driver.switchTo().frame(iframeElement);

        // ============================================================
        // STEP 7 — Interact with content inside the iframe
        //
        // After switching, findElement() searches within the iframe.
        // ============================================================
        // WebElement body = driver.findElement(By.id("tinymce"));
        // System.out.println("iframe content: " + body.getText());

        // body.clear();
        // body.sendKeys("Hello from inside the iframe!");
        // System.out.println("After typing: " + body.getText());

        // ============================================================
        // STEP 8 — Return to the main page from inside the iframe
        //
        // defaultContent() — jumps back to the top-level document
        // parentFrame()    — moves up one frame level (for nested frames)
        // ============================================================
        // driver.switchTo().defaultContent();
        // System.out.println("Back to main page title: " + driver.getTitle());

        // Now you can interact with the main page again
        // assertTrue(driver.getTitle().contains("iframe"));
    }
}
