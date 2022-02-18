package com.jochman.components.requestBodies;


public record NotificationRequest(EntityType entityType, Long entityId, String notificationMessage) {
    public enum EntityType {
        BLOGGER,
        BLOG,
        POST
    }
}
