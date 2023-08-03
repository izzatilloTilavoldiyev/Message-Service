package uz.pdp.messageservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.messageservice.dtos.UserCreateDTO;
import uz.pdp.messageservice.dtos.UserUpdateDTO;
import uz.pdp.messageservice.entity.user.User;
import uz.pdp.messageservice.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserCreateDTO> create(
            @Valid @RequestBody UserCreateDTO userCreateDTO
    ) {
        UserCreateDTO userCreateDto = userService.create(userCreateDTO);
        return ResponseEntity.ok(userCreateDto);
    }

    @PutMapping("/update")
    public ResponseEntity<UserCreateDTO> update(
            @Valid @RequestBody UserUpdateDTO updateDTO
    ) {
        UserCreateDTO update = userService.update(updateDTO);
        return ResponseEntity.ok(update);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserCreateDTO> getById(@PathVariable Long userId) {
        UserCreateDTO user = userService.getById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<User>> getAll() {
        List<User> userList = userService.getAll();
        return ResponseEntity.ok(userList);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteById(
            @PathVariable Long userId
    ) {
        userService.deleteById(userId);
        return ResponseEntity.ok("Deleted");
    }
}
