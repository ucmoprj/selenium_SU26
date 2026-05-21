package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.nio.file.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lab 09 - Assignment: Data-Driven Login Testing
 *
 * ============================================================
 * BACKGROUND: Test Case Design Rules
 * ============================================================
 *
 * This assignment uses two test design techniques:
 *
 *   1. Equivalence Partitioning (EP)
 *      Divide input data into groups (partitions) where all values
 *      in the same group are expected to behave the same way.
 *      Test one value from each partition instead of testing every value.
 *
 *      Username partitions:
 *        - Valid   : 4-12 chars, letters/numbers only, starts with a letter
 *        - Invalid : too short (<4), too long (>12), starts with number,
 *                    contains space, contains special characters
 *
 *      Password partitions:
 *        - Valid   : 6-10 chars, contains at least one uppercase letter
 *        - Invalid : too short (<6), too long (>10), no uppercase letter
 *
 *   2. Boundary Value Analysis (BVA)
 *      Errors tend to occur at the edges of input ranges.
 *      Test values AT and just OUTSIDE the boundaries.
 *
 *      Username length boundary: 3 (invalid) | 4 (valid) ... 12 (valid) | 13 (invalid)
 *      Password length boundary: 5 (invalid) | 6 (valid) ... 10 (valid) | 11 (invalid)
 *
 * ============================================================
 * TEST DATA (login_test_cases.xlsx — auto-generated at startup)
 * ============================================================
 *
 *   TC001  Valid credentials                          → success
 *   TC002  Wrong password (valid format)              → failure
 *   TC003  Wrong username (valid format)              → failure
 *   TC004  Username too short — boundary min-1 (3)   → failure
 *   TC005  Username too long  — boundary max+1 (13)  → failure
 *   TC006  Username starts with a number             → failure
 *   TC007  Username contains a space                 → failure
 *   TC008  Password too short — boundary min-1 (5)   → failure
 *   TC009  Password has no uppercase letter          → failure
 *   TC010  Both username and password empty          → failure
 *
 * ============================================================
 * *** IMPORTANT — YOU MUST REPLACE THE TEST DATA ***
 * ============================================================
 *
 *   The createTestDataFile() method contains example test cases.
 *   You MUST replace ALL rows with your OWN test cases before submitting.
 *   Using the provided example data as-is will result in a grade of zero.
 *
 *   Your 10 test cases must:
 *     - Be designed using Equivalence Partitioning and Boundary Value Analysis
 *     - Cover the username and password rules defined above
 *     - Include at least one valid (success) case and multiple invalid (failure) cases
 *     - Have a correct ExpectedResult ("success" or "failure") for each row
 *
 * ============================================================
 * YOUR TASKS
 * ============================================================
 *
 *   TASK 0 — Replace the example test data in createTestDataFile()
 *            with your own 10 test cases based on EP and BVA rules above.
 *
 *   TASK 1 — Read each test case row from the Excel file (read-only)
 *            Columns: TestCaseID | Description | Username | Password | ExpectedResult
 *
 *   TASK 2 — For each row, run the login test using Selenium
 *            Use the helper method performLogin() provided below.
 *
 *   TASK 3 — Write each result line to RESULT_TXT_FILE
 *            Format: [TC001] description | expected=success actual=success | PASS | timestamp
 *            Open BufferedWriter before the loop, write summary at the end, then close.
 *
 *   TASK 4 — After all rows are processed, assert that every test
 *            case has Status = PASS (expected matches actual).
 *
 * ============================================================
 * Test site : https://the-internet.herokuapp.com/login
 * Valid login: username=tomsmith  password=SuperSecretPassword!
 * ============================================================
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Lab09_Assignment_DataDrivenLogin {

    static final String TEST_DATA_FILE  = "target/testdata/login_test_cases.xlsx";
    static final String RESULT_TXT_FILE = "target/testdata/login_test_results.txt";

    WebDriver driver;

    // ============================================================
    // TASK 0 — Replace the example data below with YOUR OWN test cases.
    //
    // Rules reminder:
    //   Username : 4-12 chars, letters/numbers only, must start with a letter
    //   Password : 6-10 chars, at least one uppercase letter
    //
    // Keep the same column structure:
    //   { "TCxxx", "description", "username", "password", "success" or "failure" }
    //
    // Do NOT change the header row or the file path.
    // ============================================================
    @BeforeAll
    static void createTestDataFile() throws IOException {
        Path path = Paths.get(TEST_DATA_FILE);
        Files.createDirectories(path.getParent());

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("TestCases");

            // Header row
            Row header = sheet.createRow(0);
            String[] headers = {"TestCaseID", "Description", "Username", "Password", "ExpectedResult"};
            for (int i = 0; i < headers.length; i++) {
                header.createCell(i).setCellValue(headers[i]);
            }

            // *** REPLACE THESE ROWS WITH YOUR OWN TEST CASES (TASK 0) ***
            Object[][] data = {
                {"TC001", "Valid credentials",                    "tomsmith",        "SuperSecretPassword!", "success"},
                {"TC002", "Wrong password (valid format)",        "tomsmith",        "Wrong1pw",             "failure"},
                {"TC003", "Wrong username (valid format)",        "john1",           "SuperSecretPassword!", "failure"},
                {"TC004", "Username too short - boundary min-1",  "tom",             "Pass1w",               "failure"},
                {"TC005", "Username too long - boundary max+1",   "thisusername99",  "Pass1w",               "failure"},
                {"TC006", "Username starts with a number",        "1tomsmith",       "Pass1w",               "failure"},
                {"TC007", "Username contains a space",            "tom smith",       "Pass1w",               "failure"},
                {"TC008", "Password too short - boundary min-1",  "tomsmith",        "Abc1w",                "failure"},
                {"TC009", "Password has no uppercase letter",     "tomsmith",        "password1",            "failure"},
                {"TC010", "Both username and password empty",     "",                "",                     "failure"},
            };

            for (int i = 0; i < data.length; i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < data[i].length; j++) {
                    row.createCell(j).setCellValue((String) data[i][j]);
                }
            }

            try (FileOutputStream fos = new FileOutputStream(TEST_DATA_FILE)) {
                workbook.write(fos);
            }
        }
        System.out.println("Test data file created: " + Paths.get(TEST_DATA_FILE).toAbsolutePath());
    }

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        // Disable Chrome's password manager and all related popups
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--password-store=basic");
        options.addArguments("--disable-features=PasswordCheck,PasswordManager");
        options.addArguments("--incognito"); // incognito mode never saves passwords
        options.setExperimentalOption("prefs", java.util.Map.of(
            "credentials_enable_service", false,
            "profile.password_manager_enabled", false,
            "profile.password_manager_leak_detection", false
        ));

        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    // ============================================================
    // YOUR IMPLEMENTATION GOES HERE
    // ============================================================
    @Test
    @Order(1)
    void runDataDrivenLoginTests() throws IOException {

        int passCount = 0;
        int failCount = 0;

        // --------------------------------------------------------
        // TASK 1 — Open the Excel file and get the sheet
        //
        // Hint:
        //   FileInputStream fis = new FileInputStream(TEST_DATA_FILE);
        //   Workbook workbook = WorkbookFactory.create(fis);
        //   Sheet sheet = workbook.getSheet("TestCases");
        // --------------------------------------------------------
        // TODO: Open the workbook and get the "TestCases" sheet


        // --------------------------------------------------------
        // TASK 2 — Loop through each test case row (skip row 0 = header)
        //
        // Hint: sheet.getLastRowNum() gives you the index of the last row
        //
        // For each row, read:
        //   col 0 = TestCaseID     (String)
        //   col 1 = Description    (String)
        //   col 2 = Username       (String)
        //   col 3 = Password       (String)
        //   col 4 = ExpectedResult (String)  "success" or "failure"
        //
        // Then call: String actual = performLogin(username, password);
        // --------------------------------------------------------
        // TODO: Loop through rows and run performLogin() for each


        // --------------------------------------------------------
        // TASK 3 — Write each result line to the txt file
        //
        // Determine the status:
        //   String status = actual.equals(expected) ? "PASS" : "FAIL";
        //
        // Open a BufferedWriter for RESULT_TXT_FILE before the loop.
        // Inside the loop, write one line per test case:
        //   [TC001] Valid credentials ... | expected=success actual=success | PASS | 2026-05-21 11:30:45
        // After the loop, write a summary line and close the writer.
        //
        // Also print each result to console:
        //   System.out.printf("[%s] %-42s | expected=%-7s actual=%-7s | %s%n",
        //       testCaseId, description, expected, actual, status);
        // --------------------------------------------------------
        // TODO: Write results to txt file and console


        // --------------------------------------------------------
        // TASK 4 — Assert all test cases passed
        //
        // Hint: use passCount and failCount variables above
        //   assertEquals(0, failCount, failCount + " test case(s) failed");
        // --------------------------------------------------------
        // TODO: Print summary and assert failCount == 0
    }

    // ============================================================
    // PROVIDED HELPER — performs one login attempt and returns
    // "success" or "failure" based on the flash message.
    // You do NOT need to modify this method.
    // ============================================================
    String performLogin(String username, String password) {
        // Navigate to /logout first to invalidate any active server-side session,
        // then go to /login — more reliable than deleteAllCookies() alone
        driver.get("https://the-internet.herokuapp.com/logout");
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // 10s timeout — Heroku free tier can be slow
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement flash = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("flash"))
        );

        return flash.getText().contains("You logged into a secure area!") ? "success" : "failure";
    }
}
