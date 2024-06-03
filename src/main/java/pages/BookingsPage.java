package pages;

import org.openqa.selenium.By;

import java.util.ArrayList;

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

}
