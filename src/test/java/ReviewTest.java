import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.sql2o.*;
import java.util.List;

public class ReviewTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

//WILL INSTANTIATE
  @Test
  public void review_reviewInstantiatesCorrectly_True() {
    Review testReview = new Review("Bob Dillon", "The room was aight.", 4);
    assertEquals(true, testReview instanceof Review);
  }

//WILL INSTANTIATE WITH DESCRIPTION
  @Test
  public void getDescription_reviewInstantiatesWithDescription_String() {
    Review testReview = new Review("Mark Antony", "magnifico", 5);
    assertEquals("magnifico", testReview.getDescription());
  }

//WILL INSTANTIATE WITH RATING
  @Test
  public void getRating_reviewInstantiatesWithRating() {
    Review testReview = new Review("Mark Twain", "T'was awful, indeed", 2);
    assertEquals(2, testReview.getRating());
  }

  // WILL CHECK TO MAKE SURE DATE IS WORKING
  // @Test
  // public void getDate_reviewWithDate() {
  //   Review testReview = new Review("")
  // }

  //WILL EMPTY STORED FROM TEST DATABASE AFTER EACH TEST
  @Test
  public void all_emptyAtFirst() {
    assertEquals(Review.all().size(), 0);
  }

// //WILL
//   @Test
//   public void getId_assignsIdToObject() {
//     Review myReview = new Review("Shakira", "Muy Bien" 4);
//     myReview.save
//   }
}
