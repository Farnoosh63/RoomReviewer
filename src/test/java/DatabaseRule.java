import org.junit.rules.ExternalResource;
import org.sql2o.*;

  public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
      DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/room_review_test", null, null);
    }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteRoomsQuery = "DELETE FROM rooms *;";
      String deleteReviewsQuery = "DELETE FROM reviews *;";
      String deleteUsersQuery = "DELETE FROM users *;";
      con.createQuery(deleteRoomsQuery).executeUpdate();
      con.createQuery(deleteReviewsQuery).executeUpdate();
      con.createQuery(deleteUsersQuery).executeUpdate();
    }
  }
}
