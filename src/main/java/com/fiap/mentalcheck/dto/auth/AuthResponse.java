package com.fiap.mentalcheck.dto.auth;

import java.util.List;

public record AuthResponse(
    String token,
    String email,
    String nome,
    List<String> roles
) {}
