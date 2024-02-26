package com.codecool.jiratests.model;

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
    }
    @Test
    public void SuccessfulLogIn(){
        JiraSoftware login = new LogIn(driver);
        login.run();
        WebElement profileMenu = driver.findElement(By.id("user-options"));
        assertTrue(profileMenu.isDisplayed());
    }
    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}