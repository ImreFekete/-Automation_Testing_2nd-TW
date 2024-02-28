package com.codecool.jiratests.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeleteIssue extends JiraSoftware {

    public DeleteIssue(WebDriver driver) {
        super(driver);
    }

    @Override
    public void run() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String dropDownXpath = "//*[@id=\"opsbar-operations_more\"]/span";

        WebElement moreDropdown = driver.findElement(By.xpath(dropDownXpath));
        moreDropdown.click();

        String deleteIssueButtonXpath = "//*[@id=\"delete-issue\"]/a/span";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(deleteIssueButtonXpath)));
        WebElement deleteButton = driver.findElement(By.xpath(deleteIssueButtonXpath));
        deleteButton.click();

        String deleteSubmitButtonID = "delete-issue-submit";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(deleteSubmitButtonID)));
        WebElement finalDeleteButton = driver.findElement(By.id(deleteSubmitButtonID));
        finalDeleteButton.click();
    }
}
