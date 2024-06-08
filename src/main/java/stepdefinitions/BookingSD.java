package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.BookingsPage;

import java.util.ArrayList;
import java.util.Collections;

import static stepdefinitions.SharedSD.getDriver;

public class BookingSD {

    BookingsPage bookingsPage = new BookingsPage();

    @Given("I am on default locations search result screen")
    public void i_am_on_default_locations_search_result_screen() {

       getDriver().navigate().refresh();

        try {
            bookingsPage.closePopUp();
        }
        catch (Exception e)
        {

        }
    }


    @Then("I verify {string} is within the search result")
    public void i_verify_is_within_the_search_result(String expectedHotel) {

        ArrayList<String> hotelList = bookingsPage.getHotelList();

        boolean flag = false;
        for (String hotelName: hotelList) {
            System.out.println(hotelName);

            if(hotelName.contains(expectedHotel))
                flag =true;
        }

        Assert.assertTrue("Given hotel is not present in the list",
                flag);
    }

    // 70000
    @Then("I verify system displays all hotels within {string} amount")
    public void iVerifySystemDisplaysAllHotelsWithinAmount(String expectedPriceStr) {

        int expectedPrice = Integer.parseInt(expectedPriceStr);

        ArrayList<Integer> priceList = bookingsPage.getPriceList();
        System.out.println(priceList);
        boolean flag = true;
        ArrayList<Integer> greaterPriceList = new ArrayList<>();
        for (int price : priceList)
        {
            if (price>expectedPrice) {
                greaterPriceList.add(price);
                flag = false;
            }
        }

        Assert.assertTrue("some prices are greater than given price :"+expectedPrice+
                        "\nGreater price list:"+greaterPriceList,
                flag);
    }

    @When("I select option for stars as {}")
    public void iSelectOptionForStarsAs(String star) //5 star
    {
        String myStar = star.split(" ")[0];
        int starInt = Integer.parseInt(myStar);
        bookingsPage.clickRating(myStar);
        ArrayList<Integer> starList = bookingsPage.getRatingStars();

        int size = starList.size();
        int occurance = Collections.frequency(starList,starInt);

        System.out.println("size="+size);
        System.out.println("occurance="+occurance);

        boolean result = (size==occurance) ;

        Assert.assertTrue("all hotels are not having "+starInt+" Stars",result);

    }

    @Then("I verify system displays only {} hotels on search result")
    public void iVerifySystemDisplaysOnlyHotelsOnSearchResult(String star) {
    }
}
