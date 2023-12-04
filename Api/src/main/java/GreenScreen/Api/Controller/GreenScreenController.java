package GreenScreen.Api.Controller;


import GreenScreen.Api.Config.Body;
import GreenScreen.Api.Service.GreenScreenRateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;


@RestController
@RequestMapping("/rate")
public class GreenScreenController {
    @Autowired
    GreenScreenRateService greenScreenRateService;

    @PostMapping("/search")
    public ResponseEntity<Result> getPredictedRate(@RequestBody Body body) {

        ResponseEntity<Result> response;
        try {
//            throw new Exception();
            response = new ResponseEntity(greenScreenRateService.fetchPredictedRate(body), HttpStatus.OK);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());

        } catch (Exception e) {
            response = new ResponseEntity("Error fetching predicted rate.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}



//    @PostMapping("/search")
//    public ResponseEntity<Result> getPredictedRate(@RequestBody String bodyJson) {
//        ResponseEntity<Result> response;
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            Body body = objectMapper.readValue(bodyJson, Body.class);
//
//            response = new ResponseEntity(greenScreenRateService.fetchPredictedRate(body), HttpStatus.OK);
//            System.out.println(response.getStatusCode());
//            System.out.println(response.getBody());
//
//        } catch (Exception e) {
//            response = new ResponseEntity("Error fetching predicted rate.", HttpStatus.INTERNAL_SERVER_ERROR);
//            System.out.println(e);
//        }
//        return response;
//    }
//}



