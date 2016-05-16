import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class RoomTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
 public void Room_instantiatesCorrectly_true() {
   Room newRoom= new Room("room", "400 SW 6th Ave, Portland, OR, 97202", "http://lorempixel.com/400/200/");
   assertEquals(true, newRoom instanceof Room);
 }

}
