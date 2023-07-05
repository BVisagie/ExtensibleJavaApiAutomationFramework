package com.example.jsonplaceholder.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class Comment {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
