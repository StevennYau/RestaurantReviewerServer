package com.project.RestaurantReviewer;

import com.project.RestaurantReviewer.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeResource {

    /*@RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(User user){
        return user.getUserName();
    }*/

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }
}
