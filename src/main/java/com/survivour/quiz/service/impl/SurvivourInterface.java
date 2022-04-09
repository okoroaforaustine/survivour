package com.survivour.quiz.service.impl;



import com.survivour.quiz.dto.request.SurvivorRequest;
import com.survivour.quiz.dto.response.ResponseData;
import com.survivour.quiz.model.Resources;
import com.survivour.quiz.model.Survivor;

public interface SurvivourInterface {
   ResponseData AddSurvivour( SurvivorRequest survivour);
    Survivor convertSurvivourDtoToModel( SurvivorRequest survivorRequest);
    ResponseData  Update(SurvivorRequest survivour);
    ResponseData listRobotCpu() throws Exception;
    ResponseData listInfected();
    ResponseData listNonInfected();
 ResponseData PercertageOfInfected();
 ResponseData PercentageOfNonInfected();




}
