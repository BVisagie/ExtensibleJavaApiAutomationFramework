package nl.kza.restful_booker.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthUser {
    private String username;
    private String password;
}
