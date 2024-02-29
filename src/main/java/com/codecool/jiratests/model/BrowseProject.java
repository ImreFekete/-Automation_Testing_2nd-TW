package com.codecool.jiratests.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowseProject extends JiraSoftware {
    private final String projectToSearch;
    private final WebDriverWait wait;

    public BrowseProject(WebDriver driver, WebDriverWait wait, String projectToSearch) {
        super(driver);
        this.wait = wait;
        this.projectToSearch = projectToSearch;
    }

    @Override
    public void run() {
        WebElement projectsMenuPoint = driver.findElement(By.id("browse_link"));
        projectsMenuPoint.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("browse_link-content")));
        WebElement viewAllOption = driver.findElement(By.xpath("//div[@id='project_view_all']//li[@id='project_view_all_link']"));
        viewAllOption.click();
        WebElement projectSearchField = driver.findElement(By.id("project-filter-text"));
        projectSearchField.sendKeys(projectToSearch);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='" + projectToSearch + "']")));
    }
}
