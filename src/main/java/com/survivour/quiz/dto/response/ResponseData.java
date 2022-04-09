package com.survivour.quiz.dto.response;


import com.survivour.quiz.model.Survivor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseData {
   private  String responseCode;
   private String responseMessage;
   List<Survivor> data=new ArrayList<>();
   private Object result;

}
