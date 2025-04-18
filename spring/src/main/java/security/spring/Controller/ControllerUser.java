package security.spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import security.spring.Models.Users;
import security.spring.services.ServiceUser;

@RestController
public class ControllerUser {

    @Autowired
    ServiceUser serviceUser;

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return this.serviceUser.save(user);
    }


    @PostMapping("/login")
    public String login(@RequestBody Users user){
        System.out.println(user);
        return this.serviceUser.verify(user);
    }

}
