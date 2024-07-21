package academy.kata.mis.misredistest.controller;

import academy.kata.mis.misredistest.model.User;
import academy.kata.mis.misredistest.service.RedisTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/redis")
public class UserController {
    private RedisTemplateService redisTemplateService;

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam("id") Long id) {
        return ResponseEntity.ok(redisTemplateService.getUser(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(redisTemplateService.getAllUsers());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUser(@RequestParam("id") Long id) {
        return ResponseEntity.ok(redisTemplateService.deleteUser(id));
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestParam("id")  Long id,
                                        @RequestParam("name") String name,
                                        @RequestParam("last_name") String lastName) {
        System.out.println("---addUser---");
        User user = new User(id, name, lastName);
        User returnedUser = redisTemplateService.addUser(id, user);
        System.out.println(returnedUser);
        return ResponseEntity.ok(returnedUser);
    }

    @PatchMapping("/update")
    public ResponseEntity<User> updateUser(@RequestParam(name = "id") Long id,
                                           @RequestParam(name = "name", required = false) String name,
                                           @RequestParam(name = "last_name", required = false) String lastName) {
        User user = new User(id, name, lastName);
        return ResponseEntity.ok(redisTemplateService.updateUser(id, name, lastName));
    }


}
