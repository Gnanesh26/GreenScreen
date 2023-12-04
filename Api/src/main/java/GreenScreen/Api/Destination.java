package GreenScreen.Api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class Destination {
    private int order;
    private String country;
    private String city;
    private String state;
    private String zip;

}
