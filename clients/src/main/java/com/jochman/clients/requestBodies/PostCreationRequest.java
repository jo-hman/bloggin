package com.jochman.clients.requestBodies;

public record PostCreationRequest(
        String postTitle,
        String postContent) {
}
