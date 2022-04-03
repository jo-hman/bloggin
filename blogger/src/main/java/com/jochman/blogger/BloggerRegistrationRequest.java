package com.jochman.blogger;

public record BloggerRegistrationRequest(
        String nickname,
        String email,
        String roleName) {
}
