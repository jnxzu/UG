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

import J.AppUsers.model.App;

@RestController
@RequestMapping("/jsonapp")
public class AppRestController{
    @Autowired
    @Qualifier("applist")
    private List<App> applist;

    @GetMapping
    public ResponseEntity<App> getAppById(@RequestParam("appId") String appId){
        for (App app : applist) {
            if(app.getId().equals(appId)){
                return new ResponseEntity<App>(app, HttpStatus.OK);
            }
        }
        return new ResponseEntity<App>(HttpStatus.NOT_FOUND);
    }
}