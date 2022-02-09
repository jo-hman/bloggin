package com.jochman.clients.requestBodies;

public record BlogCreationRequest (
        String blogName,
        String blogDescription){
}
