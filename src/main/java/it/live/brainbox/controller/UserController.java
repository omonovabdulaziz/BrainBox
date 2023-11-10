package it.live.brainbox.controller;

import it.live.brainbox.entity.User;
import it.live.brainbox.payload.ApiResponse;
import it.live.brainbox.payload.PageSender;
import it.live.brainbox.payload.UserDTO;
import it.live.brainbox.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_USER')")
    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        return userService.updateUser(userId, userDTO);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_USER')")
    @GetMapping("/getUserById")
    public User getUserById(@RequestParam(value = "userId", required = false) Long userId) {
        return userService.getUserById(userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllUserByPage")
    public PageSender<List<User>> getAllUserByPage(@RequestParam int page, @RequestParam int size) {
        return userService.getAllUserByPage(page, size);
    }

}
