package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static stepdefinitions.SharedSD.getDriver;

public class Base {

    public WebElement webAction(By locator)
    {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(Duration.ofSeconds(50)) // max time
                .pollingEvery(Duration.ofSeconds(5)) // every 5 seconds
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(NoClassDefFoundError.class)
                .ignoring(Exception.class)
                .withMessage(
                        "WebDriver waited for 50 seconds but still " +
                                "could not find the element therefore " +
                                "Timeout Exception has been thrown");

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(locator);
            }
        });


        return  element;
    }

    public void clickOn(By locator)
    {
        webAction(locator).click();
    }
    public void setValue(By locator,String value)
    {
        webAction(locator).sendKeys(value);
    }

    public String getTextFromElement(By locator)
    {
       return webAction(locator).getText();
    }

    public ArrayList<String> getElementTextList(By locator)
    {
        List<WebElement> wbList = getDriver().findElements(locator);

        ArrayList<String> txtList = new ArrayList<>();

        for(WebElement wb : wbList)
        {
            txtList.add(wb.getText());
        }
        return txtList;
    }
}
