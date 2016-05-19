import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;

public class App {
  public static void main (String[] args){
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/user/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      User newUser = User.find(Integer.parseInt(request.params(":id")));
      model.put("userName", newUser);
      model.put("template", "templates/userPage.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/user/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String userNamedefault = request.queryParams("userName");
      String userName = userNamedefault.substring(0, 1).toUpperCase() + userNamedefault.substring(1);
      String password = request.queryParams("password");
      User newUser = new User(userName, password);
      newUser.save();
      System.out.println(newUser.getName());
      response.redirect("http://localhost:4567/user/" + newUser.getId());
      return null;
    });

    get("/searchResults", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      if (request.queryParams("search") != null) {
        String search = request.queryParams("search");
        List<Room> foundRooms = Room.searchRooms("%" + search + "%");
        model.put("foundRooms", foundRooms);
      }

        model.put("template", "templates/search-results.vtl");
        return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/user/review/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String roomType = request.queryParams("roomType");
      String address = request.queryParams("address");
      String rating = request.queryParams("rating");
      String image = request.queryParams("image");
      String review = request.queryParams("review");
      Room newRoom = new Room(roomType, address, image);
      newRoom.save();
      User newUser = User.find(Integer.parseInt(request.queryParams("userId")));
      Review newReview = new Review(review, rating, newRoom.getId(), newUser.getId());
      newReview.save();
      response.redirect("/user/" + newUser.getId() + "/review/" + newReview.getId());
      return null;
    });

    get("/user/:id/review/:rid", (request, reponse) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Review inputtedReview = Review.find(Integer.parseInt(request.params(":rid")));
      User foundUser = User.find(Integer.parseInt(request.params(":id")));
      Room foundRoom = Room.find(inputtedReview.getRoomId());
      model.put("user", foundUser);
      model.put("room", foundRoom);
      model.put("review", inputtedReview);
      model.put("template", "templates/review.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/aboutUs", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/aboutUs.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/contact", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/contact.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/contactResult", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/contactResult.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
