package com.xwcloud.cloud.sys.quartz.config;

import com.sun.istack.NotNull;
import lombok.Data;

//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;

/**
 * ModifyCronDTO
 */
@Data
public class ModifyCronDTO {
    //@NotNull(message = "the job id cannot be null")
    @NotNull
    private Integer id;

    //@NotEmpty(message = "the cron cannot be empty")
    @NotNull
    private String cron;
}
