package it.live.brainbox.controller;

import it.live.brainbox.payload.ApiResponse;
import it.live.brainbox.payload.UserDTO;
import it.live.brainbox.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/regLog")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody UserDTO registerDTO) {
        return authService.regLog(registerDTO);
    }
}
