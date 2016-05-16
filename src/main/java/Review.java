import java.time.LocalDateTime;
import org.sql2o.*;
import java.util.*;

public class Review {
  private String user_name;
  private Date date_created;
  private String description;
  private int rating;
  private int id;

  public Review(String user_name, String description, int rating) {
    this.user_name = user_name;
    this.date_created = date_created;
    this.description = description;
    this.rating = rating;
  }

  public int getId() {
    return id;
  }

  public int getRating() {
    return rating;
  }

  public Date getDate() {
    java.util.Date date_created = new java.util.Date();
    return date_created;
  }

  public String getDescription() {
    return description;
  }

  public String getUserName() {
    return user_name;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Review)) {
      return false;
    } else {
      Review newReview = (Review) obj;
      return this.getId() == newReview.getId() && this.getRating() == newReview.getRating() &&
      this.getUserName().equals(newReview.getUserName()) &&
      this.getDescription().equals(newReview.getDescription()) &&
      this.getDate().equals(newReview.getDate());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO reviews(description, user_name, date_created, rating) VALUES (:description, :user_name, :date_created, :rating)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("rating", this.rating)
        .addParameter("user_name", this.user_name)
        .addParameter("description", this.description)
        .addParameter("date_created", this.getDate())
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

  public void update(String newUser_name, String newDescription, int newRating) {
    if(newUser_name.trim().length() != 0) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE reviews SET user_name = :newUser_name WHERE id = :id;";
        con.createQuery(sql)
          .addParameter("user_name", newUser_name)
          .addParameter("id", id)
          .executeUpdate();
      }
    }

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
