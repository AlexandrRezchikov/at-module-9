package org.example.pojo;

public class UserResponse extends User {
    private String id;
    private String createdAt;
    private String updatedAt;

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
