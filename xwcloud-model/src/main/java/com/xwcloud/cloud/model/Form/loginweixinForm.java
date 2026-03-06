package com.xwcloud.cloud.model.Form;

import lombok.Data;

@Data
public class loginweixinForm {
   private String code;
   private String encryptedData;
   private String iv;
}
