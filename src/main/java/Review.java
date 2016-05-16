import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Review {
  private String user_name;
  private Date date_created;
  private String description;
  private int rating;
  private int id;

  public Review(String user_name, Date date_created, String description, int rating) {
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

  // public void save() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO reviews();";
  //     this.id = (int) con.createQuery(sql, true)
  //       .addParameter("rating", this.rating)
  //       .addParameter("name", this.name)
  //       .addParameter("instructions", this.instructions)
  //       .addParameter("ingredients", this.ingredients)
  //       .addParameter("date_created", this.getDate())
  //       .executeUpdate()
  //       .getKey();
  //   }
  // }
}
