package com.socket.controller;

import com.socket.model.User;
import com.socket.payload.Response;
import com.socket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Response response;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody User user) {
        return response.data(userService.add(user)).end();
    }

    @GetMapping
    public ResponseEntity<?> getPage(@RequestParam(required = false)String search, @RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10") int size) {
        return response.data(userService.getPage(search,page,size)).end();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        userService.delete(id);
        return response.end();
    }

    @DeleteMapping("/batch")
    public ResponseEntity<?> removeBatch(@RequestBody Long[] id){
        userService.deleteBatch(id);
        return response.end();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user) {
        return response.data(userService.update(user)).end();
    }

    @PutMapping("/batch")
    public ResponseEntity<?> updateBatch(@RequestBody List<User> listUser) {
        return response.data(userService.updateBatch(listUser)).end();
    }


}
