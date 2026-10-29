package br.com.fiap.agendafit.controller;

import br.com.fiap.agendafit.dto.LoginRequest;
import br.com.fiap.agendafit.dto.RegisterRequest;
import br.com.fiap.agendafit.dto.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequest request) {
        return;
    }

    @PostMapping("/login")
    public void login(@RequestBody @Valid LoginRequest request) {
        return;
    }
}