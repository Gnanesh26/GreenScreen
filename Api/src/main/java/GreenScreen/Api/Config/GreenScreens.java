package GreenScreen.Api.Config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GreenScreens {
    String targetBuyRate;
    String lowBuyRate;
    String highBuyRate;
    String startBuyRate;
    String fuelRate;
}
