package com.codecool.jiratests.model;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class JiraSoftwareTest {

    public static WebDriver driver = null;

    @BeforeEach
    public void setUpDriver(){
        driver = new ChromeDriver();
        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("USERNAME");
        String password = dotenv.get("PASSWORD");
        JiraSoftware login = new LogIn(driver, username, password);
        login.run();
    }
    @Test
    public void SuccessfulLogIn(){
        WebElement profileMenu = driver.findElement(By.id("user-options"));
        assertTrue(profileMenu.isDisplayed());
    }
    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}