package com.codecool.jiratests.model;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class JiraSoftwareTest {

    private static WebDriver driver = null;


    @BeforeEach
    public void setUpDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("JIRA_USERNAME");
        String password = dotenv.get("JIRA_PASSWORD");
        JiraSoftware login = new LogIn(driver, username, password);
        login.run();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
    }

    @Test
    public void successfulLogIn() {
        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("JIRA_USERNAME");
        WebElement profileMenu = driver.findElement(By.id("header-details-user-fullname"));
        assertEquals(username, profileMenu.getAttribute("data-username"));
    }

    @Test
    public void successfulLogOut() {
        LogOut logOut = new LogOut(driver);
        logOut.run();
        WebElement logOutPanel = driver.findElement(By.id("main"));
        Assertions.assertTrue(logOutPanel.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}