import java.util.List;
import org.sql2o.*;
import java.util.Arrays;
import java.util.ArrayList;


public class Room {
  private int id;
  private String type;
  private String address;
  private String url;

  public Room (String type, String address, String url){
    this.type = type;
    this.address = address;
    this.url = url;
  }
  public String getType(){
    return type;
  }
  public String getAddress(){
    return address;
  }
  public String getUrl(){
    return url;
  }
  public int getId(){
    return id;
  }
  public static List<Room> all(){
    String roomsTable = "SELECT id, type, address, url FROM rooms";
      try (Connection con = DB.sql2o.open()){
        return con.createQuery(roomsTable).executeAndFetch(Room.class);
    }
  }

  @Override
  public boolean equals(Object otherRoomInfo) {
    if(!(otherRoomInfo instanceof Room)){
      return false;
    } else {
      Room newRoom = (Room) otherRoomInfo;
      return this.getId() == newRoom.getId();
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()){
      String roomsTable = "INSERT INTO rooms (type, address, url) VALUES (:type, :address, :url)";
      this.id = (int) con.createQuery(roomsTable, true)
        .addParameter("type", this.type)
        .addParameter("address", this.address)
        .addParameter("url", this.url)
        .executeUpdate()
        .getKey();
      }
  }
  public static Room find(int id){
    try (Connection con = DB.sql2o.open()){
      String roomsTable = "SELECT * FROM rooms WHERE id=:id";
      Room room = con.createQuery(roomsTable)
      .addParameter("id", id)
      .executeAndFetchFirst(Room.class);
      return room;
    }
  }
  public List<Review>  getReviews(){
    try (Connection con = DB.sql2o.open()){
      String reviewsTable = "SELECT * FROM reviews WHERE room_id=:id";
      return con.createQuery(reviewsTable)
      .addParameter("id", this.id)
      .executeAndFetch(Review.class);
    }
  }

  public void update(String address) {
    try(Connection con = DB.sql2o.open()) {
      String roomsTable = "UPDATE rooms SET address = :address WHERE id = :id";
      con.createQuery(roomsTable)
      .addParameter("address", address)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String roomsTable = "DELETE FROM rooms WHERE id = :id;";
      con.createQuery(roomsTable)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

}
