package com.xwcloud.cloud.model.Form.stu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatestuClassbyOneForm {
    private String id;
    private String className;
}
