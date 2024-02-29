package com.codecool.jiratests.model;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JiraSoftwareSuccessfulLoginTest {
    private final static String PROFILE_MENU_ID = "header-details-user-fullname";
    private final static String PROFILE_MENU_USERNAME_ATTRIBUTE_NAME = "data-username";
    private static Dotenv dotenv = Dotenv.load();
    private static String username;
    private static String password;
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public static void initializeData() {
        username = dotenv.get("JIRA_USERNAME");
        password = dotenv.get("JIRA_PASSWORD");
    }

    @BeforeEach
    public void setUp() {
        int timeOut = 10;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        JiraSoftware login = new LogIn(driver, username, password);
        login.run();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PROFILE_MENU_ID)));
    }

    @Test
    public void successfulLogIn() {
        WebElement profileMenu = driver.findElement(By.id(PROFILE_MENU_ID));

        assertEquals(username, profileMenu.getAttribute(PROFILE_MENU_USERNAME_ATTRIBUTE_NAME));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
