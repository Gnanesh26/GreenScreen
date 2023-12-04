package GreenScreen.Api.Config;


import GreenScreen.Api.Origin;
import GreenScreen.Api.Destination;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
class Body {
    String pickupDateTime;
    String transportType;
    ArrayList<Stop> stops;

    public String toJson() {
        return Converter.convertToJson(this);
    }
}
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class NativeBody {
    public Origin origin;
    public Destination destination;
    public String equipmentType;
    public String pickupDateTime;

    public NativeBody(Origin origin, Destination destination, String equipmentType) {
        this.origin = origin;
        this.destination = destination;
        this.equipmentType = equipmentType;
//        this.pickupDateTime = LocalDateTime.now(ZoneOffset.UTC).toString();
//        this.pickupDateTime = LocalDateTime.now(ZoneOffset.UTC).toString();
    }




    public String toNative() {
        var lst = new ArrayList<Stop>();
        lst.add(new Stop(origin.getOrder(),origin.getCountry(),origin.getCity(),origin.getState(),origin.getZip()));
        lst.add(new Stop(destination.getOrder(),destination.getCountry(),destination.getCity(),destination.getState(),destination.getZip()));
        return new Body(this.pickupDateTime,this.equipmentType,lst).toJson();
    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Stop {
    int order;
    String country;
    String city;
    String state;
    String zip;

    String toJson() {
        return Converter.convertToJson(this);
    }
}