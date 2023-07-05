package com.example.jsonplaceholder.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;
}
