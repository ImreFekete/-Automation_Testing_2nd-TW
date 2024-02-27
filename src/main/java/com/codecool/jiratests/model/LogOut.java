package com.codecool.jiratests.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogOut extends JiraSoftware {

    public LogOut(WebDriver driver) {
        super(driver);
    }

    @Override
    public void run() {
        int timeout = 10;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        WebElement profileMenu = driver.findElement(By.id("user-options"));
        profileMenu.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("system")));
        WebElement logOutButton = driver.findElement(By.id("system"));
        logOutButton.click();
    }
}
