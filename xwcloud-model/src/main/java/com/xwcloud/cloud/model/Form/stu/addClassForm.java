package com.xwcloud.cloud.model.Form.stu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class addClassForm {
    private String className;
    private String zidingyiClassID;
    private String campusID;
    private String maxStuNum;
}
