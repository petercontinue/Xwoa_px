package com.xwcloud.cloud.model.Form.stu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class addKaoJiForm {
    private Long stuID;
    private Long kemuid;
    private String jibie;
    private Long kjID;
}
