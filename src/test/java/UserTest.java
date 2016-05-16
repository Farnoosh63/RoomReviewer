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
}
