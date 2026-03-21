package pratica.picpaySimplificado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pratica.picpaySimplificado.domain.User;
import pratica.picpaySimplificado.dtos.RequestUserDTO;
import pratica.picpaySimplificado.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody RequestUserDTO dto) {
        User newUser = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> allUsers = userService.allUsers();
        return ResponseEntity.ok(allUsers);
    }
}
