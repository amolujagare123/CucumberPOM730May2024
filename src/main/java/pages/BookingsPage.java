package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static stepdefinitions.SharedSD.getDriver;

public class BookingsPage extends Base {

    By hotelNameList = By.xpath("//div[@data-testid='title']");

    By popUp = By.xpath("//button[@aria-label='Dismiss sign in information.']");
    public ArrayList<String> getHotelList()
    {
       return getElementTextList(hotelNameList);
    }

    public void closePopUp()
    {
        clickOn(popUp);
    }

    By priceListElement = By.xpath("//span[@data-testid='price-and-discounted-price']");


    public ArrayList<Integer> getPriceList()
    {
        ArrayList<Integer> priceList = new ArrayList<>();
        ArrayList<String> priceListRaw = getElementTextList(priceListElement);
        System.out.println(priceListRaw);

        for (String rawPrice:priceListRaw) // ₹ 27,928
        {
            String priceWithComma = rawPrice.substring(2); // 27,928
            String priceStr = priceWithComma.replace(",",""); //27928
            int price = Integer.parseInt(priceStr); // 27928 (int)
            priceList.add(price);
        }
        return priceList;
    }


    public void clickRating(String star)
    {
        By ratingStar = By.xpath("//div[contains(@id,'filter_group_class_:r1')]//input[@name='class="+star+"']");

        getDriver().navigate().refresh();

     /*   WebDriverWait wait = new WebDriverWait( getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ratingStar));*/

        //clickOn(ratingStar);

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click()",webAction(ratingStar));

    }

    By ratingBlock = By.xpath("//div[contains(@aria-label,'out of 5')]");

    public ArrayList<Integer> getRatingStars()
    {
        getDriver().navigate().refresh();
        List<WebElement> starElementList = getDriver().findElements(ratingBlock);
        ArrayList<Integer> ratingList = new ArrayList<>();
        for (int i=0;i<starElementList.size();i++)
        {
            String label = starElementList.get(i).getAttribute("aria-label"); // 5 out of 5
            System.out.println(label);
            int rating = Integer.parseInt(label.split(" ")[0]);
            ratingList.add(rating);
        }

        System.out.println(ratingList);
        return ratingList;
    }
}
