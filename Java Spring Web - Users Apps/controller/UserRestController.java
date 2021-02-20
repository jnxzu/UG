package J.AppUsers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import J.AppUsers.model.User;;

@RestController
@RequestMapping("/jsonuser")
public class UserRestController{
    @Autowired
    @Qualifier("userlist")
    private List<User> userlist;

    @GetMapping
    public ResponseEntity<User> getAppById(@RequestParam("userId") String userId){
        for (User usr : userlist) {
            if(usr.getId().equals(userId)){
                return new ResponseEntity<User>(usr, HttpStatus.OK);
            }
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
}