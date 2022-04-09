package com.survivour.quiz.controller;


import com.survivour.quiz.dto.request.SurvivorRequest;
import com.survivour.quiz.dto.response.ResponseData;
import com.survivour.quiz.service.impl.SurvivourImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/survivour")
public class SurvivourController {
    Logger logger = LoggerFactory.getLogger(SurvivourController.class);
    private final SurvivourImpl survivourService;
    SurvivourController(SurvivourImpl survivourService){
         super();
        this.survivourService=survivourService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> AddSurvivour( @RequestBody SurvivorRequest request){
        ResponseData response=new ResponseData();
        logger.info("creating Survivour {} ",request);
        response = survivourService.AddSurvivour(request);
        return  ResponseEntity.ok().body(response);

    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update( @RequestBody SurvivorRequest request){
        ResponseData response=new ResponseData();
        logger.info("creating Survivour {} ",request);
        response = survivourService.Update(request);
        return  ResponseEntity.ok().body(response);

    }

    @GetMapping(value = "/listcpurobot")
    public ResponseEntity<?> ListCpuRobot() throws Exception {
        ResponseData response=new ResponseData();
        response = survivourService.listRobotCpu();
        return  ResponseEntity.ok().body(response);

    }

    @GetMapping(value = "/listofinfected")
    public ResponseEntity<?> ListInfectet() throws Exception {
        ResponseData response=new ResponseData();
        response = survivourService.listInfected();
        return  ResponseEntity.ok().body(response);

    }

    @GetMapping(value = "/listofnoninfected")
    public ResponseEntity<?> ListNonInfectedt() throws Exception {
        ResponseData response=new ResponseData();
        response = survivourService.listNonInfected();
        return  ResponseEntity.ok().body(response);

    }

    @GetMapping(value = "/percentageinfected")
    public ResponseEntity<?> listPercentageInfected() throws Exception {
        ResponseData response=new ResponseData();
        response = survivourService.PercertageOfInfected();
        return  ResponseEntity.ok().body(response);

    }
    @GetMapping(value = "/percentagenoninfected")
    public ResponseEntity<?>listPercentageNonInfected() throws Exception {
        ResponseData response=new ResponseData();
        response = survivourService.PercentageOfNonInfected();
        return  ResponseEntity.ok().body(response);

    }
}
