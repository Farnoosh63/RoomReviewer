import java.time.LocalDateTime;
import org.sql2o.*;
import java.util.*;

public class Review {
  private Date date_created;
  private String description;
  private String rating;
  private int id;
  private int room_id;
  private int user_id;

  public Review(String description, String rating, int room_id, int user_id) {
    this.date_created = date_created;
    this.description = description;
    this.rating = rating;
    this.room_id = room_id;
    this.user_id = user_id;
  }

  public int getId() {
    return id;
  }

  public int getUserId() {
    return user_id;
  }

  public int getRoomId() {
    return room_id;
  }

  public String getRating() {
    return rating;
  }

  public Date getDate() {
    java.util.Date date_created = new java.util.Date();
    return date_created;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Review)) {
      return false;
    } else {
      Review newReview = (Review) obj;
      return this.getId() == newReview.getId() &&
      this.getRating().equals(newReview.getRating()) &&
      this.getUserId() == newReview.getUserId() &&
      this.getRoomId() == newReview.getRoomId() &&
      this.getDescription().equals(newReview.getDescription());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO reviews(description, date_created, rating, room_id, user_id) VALUES (:description, :date_created, :rating, :room_id, :user_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("rating", this.rating)
        .addParameter("description", this.description)
        .addParameter("date_created", this.getDate())
        .addParameter("room_id", this.getRoomId())
        .addParameter("user_id", this.getUserId())
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Review> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM reviews";
      List<Review> all = con.createQuery(sql)
        .executeAndFetch(Review.class);
      return all;
    }
  }

  public static Review find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM reviews WHERE id=:id;";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Review.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM reviews WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }

  public void update( String newDescription, int newRating) {
    if(newDescription.trim().length() != 0) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE reviews SET description = :newDescription WHERE id = :id;";
        con.createQuery(sql)
          .addParameter("description", newDescription)
          .addParameter("id", id)
          .executeUpdate();
      }
    }
    if(newRating != 0) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE reviews SET rating = :newRating WHERE id = :id;";
        con.createQuery(sql)
          .addParameter("rating", newRating)
          .addParameter("id", id)
          .executeUpdate();
      }
    }
  }
}
