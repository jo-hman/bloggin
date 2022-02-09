package com.jochman.components.requestBodies;

public record BlogCreationRequest (
        String blogName,
        String blogDescription){
}
