package com.jochman.components.requestBodies;

public record PostCreationRequest(
        String postTitle,
        String postContent) {
}
