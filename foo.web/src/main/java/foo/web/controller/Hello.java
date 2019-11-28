package foo.web.controller;

import foo.web.viturdb.MyDB;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @RequestMapping("/hello")
    String hello() {
        return new MyDB().getIndex(0);
    }
}
