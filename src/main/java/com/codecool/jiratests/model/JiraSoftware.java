package com.codecool.jiratests.model;

import org.openqa.selenium.WebDriver;

public abstract class JiraSoftware {
    protected final WebDriver driver;

    public JiraSoftware(WebDriver driver) {
        this.driver = driver;
    }

    abstract public void run();
}
