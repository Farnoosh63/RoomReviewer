import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

public class UserTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void User_instantiatesCorrectly_true() {
    User newUser= new User("John Smith", "password");
    assertEquals(true, newUser instanceof User);
  }

  @Test
  public void User_instantiatesWithName_name() {
    User newUser = new User("John Smith", "password");
    assertEquals("John Smith", newUser.getName());
  }

  @Test
  public void Save_UserSavesIntoDB_true() {
    User newUser = new User("John Smith", "password");
    newUser.save();
    assertEquals(User.all().size(), 1);
  }

  @Test
  public void User_twoUserNamesAreEqual_true() {
    User newUser = new User("John Smith", "password");
    newUser.save();
    User secondUser = new User("John Smith", "password");
    secondUser.save();
    assertTrue(newUser.getName().equals(secondUser.getName()));
  }

  @Test
  public void find_findsUserInDatabase_true() {
    User newUser = new User("John Smith", "password");
    newUser.save();
    User foundUser = User.find(newUser.getId());
    assertTrue(foundUser.getName().equals(newUser.getName()));
  }

  @Test
  public void delete_deletesUserFromDB_true() {
    User newUser = new User("John Smith", "password");
    newUser.save();
    newUser.delete();
    assertTrue(newUser.all().size() == 0);
  }

  @Test
  public void update_updateUserName_name() {
    User newUser = new User("John Smith", "password");
    newUser.save();
    newUser.update("Bob bob", "newPassword");
    assertEquals("Bob bob", User.find(newUser.getId()).getName());
  }

  @Test
  public void getReviews_userGetsReviews_true() {
    User newUser = new User("John Smith", "password");
    newUser.save();
    Review newReview = new Review ("description", "3", 1, newUser.getId());
    newReview.save();
    assertTrue(newUser.getReviews().size() == 1);
  }
}
