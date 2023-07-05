package nl.kza.jsonplaceholder.model.user;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class Geo {
    private String lat;
    private String lng;
}
