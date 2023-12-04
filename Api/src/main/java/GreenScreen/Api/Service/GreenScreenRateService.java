package GreenScreen.Api.Service;


import GreenScreen.Api.Config.*;
import GreenScreen.Api.Destination;
import GreenScreen.Api.Origin;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class GreenScreenRateService {
//    @Autowired

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public boolean authenticate() {
        String url = "https://testapi.greenscreens.ai/v1/auth/token";
        String clientId = "demo-pgttrucking";
        String clientSecret = "HO2HlvLGGidAEnhD1X1BPgQOWQr84mdG";

        String formData = "grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                ObjectMapper objectMapper = new ObjectMapper();

                JsonNode jsonNode = objectMapper.readTree(responseBody);
                String accessToken = jsonNode.get("access_token").asText();
                Date expiry = new Date();
                String tokenType = "";

                AuthenticationState.accessToken = accessToken;
                AuthenticationState.expiry = expiry;
                AuthenticationState.tokenType = tokenType;
//                authenticationState.setExpiry(expiry);
//                authenticationState.setTokenType(tokenType);
                return true;
            } else {
                System.out.println("authenticate " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("authenticate " + e);
        }
        return false;
    }


//    public Result fetchPredictedRate(Body body) {
//        String predictionUrl = "https://testapi.greenscreens.ai/v3/prediction/rates";
//
//        // Set pickupDateTime to current system time if it's not provided in the Body object
//        if (body.getPickupDateTime() == null || body.getPickupDateTime().isEmpty()) {
//            body.setPickupDateTime(LocalDateTime.now().toString());
//        }
//
//        try {
//            HttpRequest predictionRequest = HttpRequest.newBuilder()
//                    .uri(URI.create(predictionUrl))
//                    .header("Authorization", "Bearer " + AuthenticationState.accessToken)
//                    .header("Content-Type", "application/json")
//                    .POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(body)))
//                    .build();
//
//            HttpResponse<String> response = httpClient.send(predictionRequest, HttpResponse.BodyHandlers.ofString());
//            System.out.println("fetchPredictedRate() " + response.statusCode());
//
//            if (response.statusCode() == 200) {
//                return processResponse(response.body());
//            }
//            System.out.println("ok");
//
//            if (response.statusCode() == 401 && authenticate()) {
//                return fetchPredictedRate(body);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    private Result processResponse(String responseBody) {
//        try {
//            Result result = new Result();
//            result.setMessage("your rates");
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode jsonNode = objectMapper.readTree(responseBody);
//            GreenScreens gs = new GreenScreens();
//            DataObj x = new DataObj();
//            Rates r = new Rates();
//            x.setRates(r);
//            result.setData(x);
//
//            gs.setTargetBuyRate(jsonNode.get("targetBuyRate").asText());
//            gs.setLowBuyRate(jsonNode.get("lowBuyRate").asText());
//            gs.setHighBuyRate(jsonNode.get("highBuyRate").asText());
//            gs.setStartBuyRate(jsonNode.get("startBuyRate").asText());
//            gs.setFuelRate(jsonNode.get("fuelRate").asText());
//            r.setGreenScreens(gs);
//
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}




        public Result fetchPredictedRate(Body body)

        {

            String predictionUrl = "https://testapi.greenscreens.ai/v3/prediction/rates";

            HttpRequest predictionRequest = HttpRequest.newBuilder()
                    .uri(URI.create(predictionUrl))
                    .header("Authorization", "Bearer " + AuthenticationState.accessToken)
                    .header("Content-Type", "application/json")
//                    .POST(HttpRequest.BodyPublishers.ofString(body.toJson()))
                    .POST(HttpRequest.BodyPublishers.ofString(body.toJson()))
                    .build();

            try {
                var response = httpClient.send(predictionRequest, HttpResponse.BodyHandlers.ofString());
                System.out.println("fetchPredictedRate()" + response.statusCode());
//            System.out.println(response.body());
//            System.out.println(AuthenticationState.accessToken);

                if (response.statusCode() == 200) {
                    Result result = new Result();
                    result.setMessage("your rates");
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(response.body());
                    GreenScreens gs = new GreenScreens();
                    DataObj x = new DataObj();
                    Rates r = new Rates();
                    x.setRates(r);
                    result.setData(x);
                    gs.setTargetBuyRate(jsonNode.get("targetBuyRate").asText());
                    gs.setLowBuyRate(jsonNode.get("lowBuyRate").asText());
                    gs.setHighBuyRate(jsonNode.get("highBuyRate").asText());
                    gs.setStartBuyRate(jsonNode.get("startBuyRate").asText());
                    gs.setFuelRate(jsonNode.get("fuelRate").asText());
                    r.setGreenScreens(gs);
                    return result;
                }

                if (response.statusCode() == 401) {
                    if (!authenticate()) {
                        System.out.println("failed");
                    } else return fetchPredictedRate(body);
                }
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
            return null;
        }
    }


