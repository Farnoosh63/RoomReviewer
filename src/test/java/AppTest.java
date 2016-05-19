
import org.sql2o.*; //for DB support
import org.junit.*; // for @Before and @After
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.junit.Assert.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
    public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Welcome to");
  }

  @Test
  public void displayContactPage() {
    goTo("http://localhost:4567/contact");
    assertThat(pageSource()).contains("John Doe");
  }

  @Test
  public void displayAboutUsPage() {
    goTo("http://localhost:4567/aboutUs");
    assertThat(pageSource()).contains("Scott McIntire");
  }

  @Test
  public void getUserInfo() {
    goTo("http://localhost:4567");
    User newUser = new User("Michael Jackson","password");
    newUser.save();
    String userUrl = String.format("http://localhost:4567/user/%d", newUser.getId());
    goTo(userUrl);
    assertThat(pageSource()).contains("Welcome Michael Jackson");
  }

  @Test
  public void createUserReview() {
    goTo("http://localhost:4567");
    User newUser = new User("Michael Jackson","iAMdeaD");
    newUser.save();
    String userUrl = String.format("http://localhost:4567/user/%d", newUser.getId());
    goTo(userUrl);
    Room newRoom= new Room("room", "400 SW 6th Ave, Portland, OR, 97202", "http://lorempixel.com/400/200/");
    newRoom.save();
    Review testReview = new Review("The room was aight.", "4", newRoom.getId(), newUser.getId());
    testReview.save();
    String reviewUrl = String.format("http://localhost:4567/user/%d/review/%d", newUser.getId(),  testReview.getId());
    goTo(reviewUrl);
    assertThat(pageSource()).contains("The room was aight.");
  }

  @Test
  public void searchRoomReview() {
    goTo("http://localhost:4567");
    User newUser = new User("Michael Jackson","password");
    newUser.save();
    String userUrl = String.format("http://localhost:4567/user/%d", newUser.getId());
    goTo(userUrl);
    Room newRoom= new Room("Apartment", "400 SW 6th Ave, Portland, OR, 97202", "http://lorempixel.com/400/200/");
    newRoom.save();
    Review testReview = new Review("The room was aight.", "4", newRoom.getId(), newUser.getId());
    testReview.save();
    String url = String.format("http://localhost:4567/searchResults?search=Portland");
    goTo(url);
    assertThat(pageSource()).contains("The room was aight.");
  }

}
