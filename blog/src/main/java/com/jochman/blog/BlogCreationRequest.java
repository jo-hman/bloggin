package com.jochman.blog;

public record BlogCreationRequest (
        String blogName,
        String blogDescription){
}
