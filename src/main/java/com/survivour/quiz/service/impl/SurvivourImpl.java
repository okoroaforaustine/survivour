package com.survivour.quiz.service.impl;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.survivour.quiz.dto.request.SurvivorRequest;
import com.survivour.quiz.dto.response.ResponseData;
import com.survivour.quiz.model.Resources;
import com.survivour.quiz.model.Survivor;
import com.survivour.quiz.repository.impl.SurvivorRepository;
import com.survivour.quiz.util.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.util.List;
import java.util.Optional;

@Service
public class SurvivourImpl implements  SurvivourInterface {
    final static Logger logger = LoggerFactory.getLogger(SurvivourImpl.class);
    private final SurvivorRepository survivorRepository;
    private final RestUtil restUtil;
    ObjectMapper objectMapper=new ObjectMapper();
    @Value("${robbotcpu.url}")
    private String url;

    SurvivourImpl ( SurvivorRepository survivorRepository, RestUtil restUtil ) {
        super();
        this.survivorRepository = survivorRepository;
        this.restUtil = restUtil;

    }

    @Override
    public ResponseData AddSurvivour ( SurvivorRequest survivorRequest ) {
        ResponseData responseData = new ResponseData();
        Survivor respObj=null;

        try {

            if (Resources.AMMUNITION.name().equalsIgnoreCase(survivorRequest.getResource())) {

                Survivor survivorData = convertSurvivourDtoToModel(survivorRequest);
                survivorData.setResources(Resources.AMMUNITION);
                respObj=  survivorRepository.save(survivorData);
            }
          else  if (Resources.FOOD.name().equalsIgnoreCase(survivorRequest.getResource())) {
                Survivor survivorData = convertSurvivourDtoToModel(survivorRequest);
                survivorData.setResources(Resources.FOOD);
                respObj=  survivorRepository.save(survivorData);
            }
          else  if (Resources.MEDICATION.name().equalsIgnoreCase(survivorRequest.getResource())) {
                Survivor survivorData = convertSurvivourDtoToModel(survivorRequest);
                survivorData.setResources(Resources.MEDICATION);
                respObj=  survivorRepository.save(survivorData);
            }

         else   if (Resources.WATER.name().equalsIgnoreCase(survivorRequest.getResource())) {
                Survivor survivorData = convertSurvivourDtoToModel(survivorRequest);
                survivorData.setResources(Resources.WATER);
                respObj=  survivorRepository.save(survivorData);
            } else {
                responseData.setResponseCode("99X");
                responseData.setResponseCode("No Specified Resource");
                return responseData;

            }

            responseData.setResponseCode("00");
            responseData.setResponseMessage("Suvivour has been created");
            responseData.setResult(respObj);
            return responseData;

        } catch (Exception ex) {
            logger.info("Exception occurs {} ",ex);
            responseData.setResponseCode("99");
            responseData.setResponseMessage("error occured contact admin");
            return responseData;

        }

    }


    @Override
    public Survivor convertSurvivourDtoToModel ( SurvivorRequest survivorRequest) {
        Survivor survivour = new Survivor();
        survivour.setAge(survivorRequest.getAge());
        survivour.setGender(survivorRequest.getGender());
        survivour.setName(survivorRequest.getName());
        survivour.setInfected(survivorRequest.isInfected());



        return survivour;
    }

    @Override
    public ResponseData Update ( SurvivorRequest survivour ) {
        ResponseData responseData = new ResponseData();
        Resources resources=null;
        Survivor survivorObj =null;
        try {

            if (Resources.AMMUNITION.toString().equalsIgnoreCase(survivour.getResource())) {
                Survivor survivorData = convertSurvivourDtoToModel(survivour);
                Survivor getSurviour=survivorRepository.findById(survivour.getId());
                if(getSurviour!=null){
                    Survivor obj= objectMapper.convertValue(getSurviour,Survivor.class);
                    obj.setResources(Resources.AMMUNITION);
                    obj.setAge(survivorData.getAge());
                    obj.setGender(survivorData.getGender());
                    obj.setInfected(survivorData.isInfected());
                    obj.setName(survivorData.getName());
                    obj.setLocation(survivorData.getLocation());
                    survivorObj= survivorRepository.save(obj);
                    responseData.setResponseCode("00");
                    responseData.setResponseCode("Suvivour has been created");
                    responseData.setResult(survivorObj);
                    return responseData;
                }else{
                    responseData.setResponseCode("xx");
                    responseData.setResponseCode("No data for this surviour");
                    return responseData;
                }

            }
          else  if (Resources.FOOD.toString().equalsIgnoreCase(survivour.getResource())) {
                Survivor survivorData = convertSurvivourDtoToModel(survivour);
                Survivor getSurviour=survivorRepository.findById(survivour.getId());
                if(getSurviour!=null){
                    Survivor obj= objectMapper.convertValue(getSurviour,Survivor.class);
                    obj.setResources(Resources.FOOD);
                    obj.setAge(survivorData.getAge());
                    obj.setGender(survivorData.getGender());
                    obj.setInfected(survivorData.isInfected());
                    obj.setName(survivorData.getName());
                    obj.setLocation(survivorData.getLocation());
                    survivorObj= survivorRepository.save(obj);
                    responseData.setResponseCode("00");
                    responseData.setResponseCode("Suvivour has been created");
                    responseData.setResult(survivorObj);

                    return responseData;
                }else{
                    responseData.setResponseCode("xx");
                    responseData.setResponseCode("No data for this surviour");
                    return responseData;
                }

            }
          else  if (Resources.MEDICATION.toString().equalsIgnoreCase(survivour.getResource())) {
                Survivor survivorData = convertSurvivourDtoToModel(survivour);
                Survivor getSurviour=survivorRepository.findById(survivour.getId());
                if(getSurviour!=null){
                    Survivor obj= objectMapper.convertValue(getSurviour,Survivor.class);
                    obj.setAge(survivorData.getAge());
                    obj.setGender(survivorData.getGender());
                    obj.setInfected(survivorData.isInfected());
                    obj.setName(survivorData.getName());
                    obj.setLocation(survivorData.getLocation());
                     obj.setResources(Resources.MEDICATION);
                    survivorObj= survivorRepository.save(obj);
                    responseData.setResponseCode("00");
                    responseData.setResponseCode("Suvivour has been created");
                    responseData.setResult(survivorObj);
                    return responseData;
                }else{
                    responseData.setResponseCode("xx");
                    responseData.setResponseCode("No data for this surviour");
                    return responseData;
                }

            }

          else  if (Resources.WATER.toString().equalsIgnoreCase(survivour.getResource())) {
                Survivor survivorData = convertSurvivourDtoToModel(survivour);
                Survivor getSurviour=survivorRepository.findById(survivour.getId());
                if(getSurviour!=null){
                    Survivor obj= new Survivor();
                      obj.setAge(survivorData.getAge());
                      obj.setGender(survivorData.getGender());
                      obj.setInfected(survivorData.isInfected());
                      obj.setName(survivorData.getName());
                      obj.setLocation(survivorData.getLocation());
                      obj.setResources(Resources.WATER);
                    survivorObj= survivorRepository.save(obj);
                    responseData.setResponseCode("00");
                    responseData.setResponseCode("Suvivour has been created");
                    responseData.setResult( survivorObj);
                    return responseData;
                }else{
                    responseData.setResponseCode("xx");
                    responseData.setResponseCode("No data for this surviour");
                    return responseData;
                }

            } else {
                responseData.setResponseCode("99X");
                responseData.setResponseCode("No Specified Resource");
                return responseData;

            }





        } catch (Exception ex) {
            logger.info("Exception occurs {} ",ex);
            responseData.setResponseCode("99");
            responseData.setResponseCode("error occured contact admin");
            return responseData;

        }

    }

    @Override
    public ResponseData listRobotCpu () throws Exception {
        ResponseData responseData = new ResponseData();
        try {
            ResponseEntity response = restUtil.setUrl(url).get(String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                responseData.setResponseCode("000");
                responseData.setResponseMessage("Successful");
                responseData.setResult(response.getBody().toString());
            }else{

                responseData.setResponseCode("99x");
                responseData.setResponseCode("Failed Operation");

            }


        }catch (Exception ex){
            logger.info("Exception occurs {} ",ex);
            responseData.setResponseCode("99");
            responseData.setResponseCode("Failed Operation");

        }

        return responseData;
    }

    @Override
    public ResponseData listInfected () {
        ResponseData responseData = new ResponseData();
        try {
            List<Survivor> survivorsData = survivorRepository.getListOfInfectedSurvivour();

            if (survivorsData.size() == 0) {
                responseData.setResponseCode("99");
                responseData.setResponseCode("No Infected person");
            } else {

                responseData.setResponseCode("00");
                responseData.setResponseCode("Succesful");
                responseData.setData(survivorsData);
            }
        }catch(Exception ex){

            responseData.setResponseCode("99");
            responseData.setResponseCode("No Infected person");
            return  responseData;
        }

        return  responseData;

    }

    @Override
    public ResponseData listNonInfected () {
        ResponseData responseData = new ResponseData();
        try {
            List<Survivor> survivorsData = survivorRepository.getListOfNonInfectedSurvivour();

            if (survivorsData.size() == 0) {
                responseData.setResponseCode("99");
                responseData.setResponseCode("No Infected person");
            } else {

                responseData.setResponseCode("00");
                responseData.setResponseCode("Succesful");
                responseData.setData(survivorsData);
            }
        }catch(Exception ex){

            responseData.setResponseCode("99");
            responseData.setResponseCode("No Infected person");
            return  responseData;
        }

        return  responseData;
    }

    @Override
    public ResponseData PercertageOfInfected () {
        ResponseData responseData = new ResponseData();
        long total=survivorRepository.countSurviour();
       long infected=survivorRepository.countInfected();
       if(total>0) {
           long percentage = infected / total * 100;
           if (percentage == 0) {
               responseData.setResponseCode("99");
               responseData.setResponseCode("No Data");
               return responseData;
           } else {
               responseData.setResponseCode("00");
               responseData.setResponseCode("Successful");
               String data = String.valueOf(percentage + "%");
               responseData.setResult(data);
           }
       }else{
           responseData.setResponseCode("99");
           responseData.setResponseCode("No Data");
           return responseData;

       }


        return responseData;
    }

    @Override
    public ResponseData PercentageOfNonInfected () {
        ResponseData responseData = new ResponseData();
        long total=survivorRepository.countSurviour();
        long nonInfected=survivorRepository.countNonInfected();
        if(total>0) {
        long percentage=nonInfected/total*100;

            if (percentage == 0) {
                responseData.setResponseCode("99");
                responseData.setResponseCode("No Data");
                return responseData;
            } else {
                responseData.setResponseCode("00");
                responseData.setResponseCode("Successful");
                String data = String.valueOf(percentage + "%");
                responseData.setResult(data);
            }
        }else{
            responseData.setResponseCode("99");
            responseData.setResponseCode("No Data");
            return responseData;

        }

        return responseData;
    }


}
