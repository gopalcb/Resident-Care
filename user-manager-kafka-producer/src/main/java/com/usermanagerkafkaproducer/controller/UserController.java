package com.usermanagerkafkaproducer.controller;

import com.usermanagerkafkaproducer.entity.Topic;
import com.usermanagerkafkaproducer.entity.User;
import com.usermanagerkafkaproducer.service.MessageService;
import com.usermanagerkafkaproducer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    /** PostMapping **/
    @PostMapping("/add-user")
    public List<User> addUser(@RequestBody User user){
        // save user
        User userObj = userService.saveUser(user);

        if (userObj.getId() > 0){
            // send message to the kafka topic
            messageService.send(userObj, false, Topic.email_manager_topic.toString());
        }

        //return userService.getTenants();
        return userService.getAllUsers();
    }

    @PostMapping("/add-users")
    public List<User> addUsers(@RequestBody List<User> users){
        return userService.saveUsers(users);
    }

    @PostMapping("/delete-user/{id}")
    public boolean deleteUsers(@PathVariable int id){
        return userService.deleteUser(id);
    }

    /** GetMapping **/
    @GetMapping("/users")
    public List<User> findAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @GetMapping("/user")
    @ResponseBody
    public User findUserByEmail(@RequestParam() String email){
        return userService.getUserByEmail(email);
    }

    @GetMapping("/users-by-property-type")
    @ResponseBody
    public List<User> findUserByPropertyType(@RequestParam() int propertyId, @RequestParam() String key){
        return userService.getUsersByPropertyType(propertyId, key);
    }

    @GetMapping("/tenants")
    @ResponseBody
    public List<User> findTenants(){
        List<User> users = userService.getAllUsers();
        List<User> tenants = users.stream().filter(f -> f.getRole() == "tenant").collect(Collectors.toList());
        return tenants;
    }

    @GetMapping("/authenticate-user")
    @ResponseBody
    public User authenticateUser(@RequestParam() String email, @RequestParam() String password){
        return userService.authUser(email, password);
    }

    @PostMapping("/delete")
    public boolean deleteApt(@RequestBody int id){
        return userService.deleteUser(id);
    }
}
