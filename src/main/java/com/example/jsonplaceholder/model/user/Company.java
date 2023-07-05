package com.example.jsonplaceholder.model.user;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;
}
