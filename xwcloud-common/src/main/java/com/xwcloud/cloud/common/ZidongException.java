package com.xwcloud.cloud.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  //生成有参数的构造方法
@NoArgsConstructor  //生成物参数的构造方法
public class ZidongException extends RuntimeException {
    private Integer code;

    private String msg;
}
