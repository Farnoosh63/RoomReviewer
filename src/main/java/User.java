import java.util.List;
import org.sql2o.*;
import java.util.*;

public class User {
  private String name;
  private String password;
  private int id;

  public User(String name, String password) {
    this.name = name;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof User)) {
      return false;
    } else {
      User newUser = (User) obj;
      return this.getId() == newUser.getId() && this.getName() == newUser.getName() &&
      this.getPassword().equals(newUser.getPassword());
    }
  }

  public static List<User> all(){
    String sql = "SELECT name, password FROM users";
      try (Connection con = DB.sql2o.open()){
        return con.createQuery(sql).executeAndFetch(User.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO users(name, password) VALUES (:name, :password)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("password", this.password)
        .executeUpdate()
        .getKey();
    }
  }

  public static User find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM users WHERE id=:id;";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(User.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM users WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }

  public List<Review> getReviews() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM reviews WHERE user_id = :user_id";
      return con.createQuery(sql)
        .addParameter("user_id", this.id)
        .executeAndFetch(Review.class);
    }
  }

  public void update(String newName, String newPassword) {
    if(newName.trim().length() != 0) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE users SET name = :name WHERE id = :id;";
        con.createQuery(sql)
          .addParameter("name", newName)
          .addParameter("id", id)
          .executeUpdate();
      }
    }
    if(newPassword.trim().length() != 0) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE users SET password = :password WHERE id = :id;";
        con.createQuery(sql)
          .addParameter("password", newPassword)
          .addParameter("id", id)
          .executeUpdate();
      }
    }
  }
}
