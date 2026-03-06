package com.xwcloud.cloud.model.Form.stu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class newclassForm {
   private String id;
   private String campusID;
   private String className;
   private String zidingyiClassID;
   private int maxStuNum;
}
