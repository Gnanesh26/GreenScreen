package GreenScreen.Api.Config;


import GreenScreen.Api.Origin;
import GreenScreen.Api.Destination;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;

class Converter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertToJson(Object object) {
        try {

            return objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle the exception based on your application's requirements
            return null;
        }
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Body {
    String pickupDateTime;
    String transportType;
    ArrayList<Stop> stops;

    public String toJson() {
        return Converter.convertToJson(this);
    }
}
public class NativeBody {
    private Origin origin;
    private Destination destination;
    private String transportType;
    private String pickupDateTime;

    public NativeBody(Origin origin, Destination destination, String transportType) {
        this.origin = origin;
        this.destination = destination;
        this.transportType = transportType;
        this.pickupDateTime = LocalDateTime.now(ZoneOffset.UTC).toString();
    }

//    public String toJson() {
//        return Converter.convertToJson(this);}
//    }

    public String toNative() {
        return new Body( origin,  destination, transportType) ;}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Stop {
    String order;
    String country;
    String city;
    String state;
    String zip;

    String toJson() {
        return Converter.convertToJson(this);
    }
}

