package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Lab 01 - Browser Setup & Basic Navigation
 *
 * Learning Objectives:
 *   1. Set up ChromeDriver using WebDriverManager (no manual driver download needed)
 *   2. Open and close a browser window
 *   3. Navigate to a URL
 *   4. Use browser history: back, forward, refresh
 *   5. Control the browser window size
 *
 * Instructions:
 *   - Uncomment one STEP block at a time, then run the test
 *   - Read each comment carefully before uncommenting
 *   - Run: mvn test -Dtest=Lab01_BrowserSetup
 *
 * Test site: https://the-internet.herokuapp.com
 */
public class Lab01_BrowserSetup {

    WebDriver driver;

    // @BeforeEach runs once before EACH @Test method
    @BeforeEach
    void setUp() {

        // ============================================================
        // STEP 1 — Download and configure ChromeDriver automatically
        //
        // WebDriverManager checks your installed Chrome version and
        // downloads the matching chromedriver binary for you.
        // Without this, you would get a "driver not found" error.
        // System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
        // driver = new ChromeDriver();
        // ============================================================
        // WebDriverManager.chromedriver().setup();

        // ============================================================
        // STEP 2 — Launch a new Chrome browser window
        //
        // new ChromeDriver() opens a real browser window.
        // The WebDriver interface lets us control it programmatically.
        // ============================================================
        // driver = new ChromeDriver();
    }

    // @AfterEach runs once after EACH @Test method
    @AfterEach
    void tearDown() {

        // ============================================================
        // STEP 3 — Close the browser after each test
        //
        // driver.quit() closes all browser windows and ends the session.
        // driver.close() closes only the current window (use quit() in teardown).
        // Always clean up — otherwise browser processes pile up.
        // ============================================================
        // if (driver != null) {
        //     driver.quit();
        // }
    }

    @Test
    void openBrowserAndGetTitle() {

        // ============================================================
        // STEP 4 — Navigate to a URL
        //
        // driver.get(url) loads the given URL and waits for the page to load.
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com");

        // ============================================================
        // STEP 5 — Read the page title
        //
        // getTitle() returns the text in the browser's title bar (<title> tag).
        // ============================================================
        // String title = driver.getTitle();
        // System.out.println("Page title: " + title);

        // ============================================================
        // STEP 6 — Read the current URL
        //
        // getCurrentUrl() returns the full URL of the page currently loaded.
        // Useful for verifying redirects or navigation outcomes.
        // ============================================================
        // String url = driver.getCurrentUrl();
        // System.out.println("Current URL: " + url);
    }

    @Test
    void browserHistory() {

        // ============================================================
        // STEP 7 — Navigate between pages and use history
        //
        // navigate().to()  — same as get(), but part of the Navigate API
        // navigate().back()    — browser Back button
        // navigate().forward() — browser Forward button
        // navigate().refresh() — browser Refresh (F5)
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com");
        // driver.navigate().to("https://the-internet.herokuapp.com/login");
        // System.out.println("On: " + driver.getCurrentUrl());

        // driver.navigate().back();
        // System.out.println("Back to: " + driver.getCurrentUrl());

        // driver.navigate().forward();
        // System.out.println("Forward to: " + driver.getCurrentUrl());

        // driver.navigate().refresh();
        // System.out.println("Refreshed: " + driver.getCurrentUrl());
    }

    @Test
    void windowManagement() {

        // ============================================================
        // STEP 8 — Resize and maximize the browser window
        //
        // manage().window() provides access to window controls.
        // maximize() — fills the screen
        // setSize()   — sets an exact pixel width/height
        // ============================================================
        // driver.get("https://the-internet.herokuapp.com");

        // driver.manage().window().maximize();
        // System.out.println("Window maximized");

        // driver.manage().window().setSize(new Dimension(1280, 800));
        // System.out.println("Window resized to 1280x800");
    }
}
