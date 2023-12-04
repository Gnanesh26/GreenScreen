package GreenScreen.Api.Controller;

import GreenScreen.Api.Config.NativeBody;
import GreenScreen.Api.Config.Result;
import GreenScreen.Api.Service.GreenScreenRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rate")
public class GreenScreenController {
    @Autowired
    GreenScreenRateService greenScreenRateService;

    @PostMapping("/search")
    public ResponseEntity<Result> getPredictedRate(@RequestBody NativeBody body) {

        ResponseEntity<Result> response;
        try {
            Result res = greenScreenRateService.fetchPredictedRate(body);
            if(res.getStatusCode() == 0 || res.getStatusCode() == 200 ){
                response = new ResponseEntity(res, HttpStatusCode.valueOf(200));
            }
            else response = new ResponseEntity(null, HttpStatusCode.valueOf(res.getStatusCode()));
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());

        } catch (Exception e) {
            response = new ResponseEntity("Error fetching predicted rate.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}




