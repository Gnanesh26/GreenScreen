package GreenScreen.Api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public  class Origin {
    private int order;
    private String country;
    private String city;
    private String state;
    private String zip;

}
