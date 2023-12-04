package GreenScreen.Api.Config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    String message;
    DataObj data;

    @JsonIgnore
    int statusCode;
}

