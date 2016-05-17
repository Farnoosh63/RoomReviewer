import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

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
   public void save_savesRoomIntoDataBase_true() {
     String roomType ="type";
     String roomAddress ="address";
     String roomUrl ="url";
     Room newRoom = new Room(roomType, roomAddress, roomUrl);
     newRoom.save();
     assertTrue(Room.all().get(0).equals(newRoom));
   }
   @Test
   public void save_assignsIdToRoom_int() {
     String roomType ="type";
     String roomAddress ="address";
     String roomUrl ="url";
     Room newRoom = new Room(roomType, roomAddress, roomUrl);
     newRoom.save();
     Room savedRoom = Room.all().get(0);
     assertEquals(newRoom.getId(), savedRoom.getId());
   }

   @Test
   public void find_findsRoomInDatabase_true() {
     String roomType ="type";
     String roomAddress ="address";
     String roomUrl ="url";
     Room newRoom = new Room(roomType, roomAddress, roomUrl);
     newRoom.save();
     Room savedRoom = Room.find(newRoom.getId());
     assertTrue(newRoom.equals(savedRoom));
   }
// when we create user object we run this test
   @Test
   public void add_addsReviewsToRoom_true() {
     String roomType ="type";
     String roomAddress ="address";
     String roomUrl ="url";
     Room newRoom = new Room(roomType, roomAddress, roomUrl);
     newRoom.save();
     User newUser = new User("user_name", "user_password");
     newUser.save();
     Review newReview = new Review ("description", "3", newRoom.getId(), newUser.getId());
     newReview.save();
     assertTrue(newRoom.getReviews().contains(newReview));
   }

   @Test
    public void update_RoomAddress_true() {
     String roomType ="type";
     String roomAddress ="address";
     String roomUrl ="url";
     Room newRoom = new Room(roomType, roomAddress, roomUrl);
     newRoom.save();
     newRoom.update("address2");
     assertEquals("address2", Room.find(newRoom.getId()).getAddress());
   }

   @Test
   public void delete_Room_true() {
     String roomType ="type";
     String roomAddress ="address";
     String roomUrl ="url";
     Room newRoom = new Room(roomType, roomAddress, roomUrl);
     newRoom.save();
     int newRoomId = newRoom.getId();
     newRoom.delete();
     assertEquals(null, Room.find(newRoomId));
   }


}
