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
  @Test
  public void getRoom_type_address_url_String() {
    Room newRoom = new Room("room", "400 SW 6th Ave, Portland, OR, 97202", "http://lorempixel.com/400/200/");
    assertEquals("room", newRoom.getType());
    assertEquals("400 SW 6th Ave, Portland, OR, 97202", newRoom.getAddress());
    assertEquals("http://lorempixel.com/400/200/", newRoom.getUrl());
  }
  @Test
  public void all_emptyAtFirst_0() {
    assertEquals(0, Room.all().size());
  }
  @Test
  public void equals_returnsTrueIf_type_address_url_AretheSame_true() {
    Room inputRoom = new Room("room", "400 SW 6th Ave, Portland, OR, 97202", "http://lorempixel.com/400/200/");
    Room databaseRoom = new Room("room", "400 SW 6th Ave, Portland, OR, 97202", "http://lorempixel.com/400/200/");
    assertTrue(inputRoom.equals(databaseRoom));
  }

   @Test
   public void save_saveRoomIntoDataBase_true() {
     String roomType ="type";
     String roomAddress ="address";
     String roomUrl ="url";
     Room expectedRoom = new Room(roomType, roomAddress, roomUrl);
     expectedRoom.save();
     assertTrue(Room.all().get(0).equals(expectedRoom));
   }
   @Test
   public void save_assignsIdToRoom_int() {
     String roomType ="type";
     String roomAddress ="address";
     String roomUrl ="url";
     Room expectedRoom = new Room(roomType, roomAddress, roomUrl);
     Room savedRoom = Room.all().get(0);
     assertEquals(expectedRoom.getId(), savedRoom.getId());
   }





}
