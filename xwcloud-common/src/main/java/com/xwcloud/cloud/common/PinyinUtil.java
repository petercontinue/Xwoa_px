package com.xwcloud.cloud.common;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {

    public static String ChineseToPinyin(String chinese){
        StringBuilder pyBuilder = new StringBuilder();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i=0;i<chinese.length();i++){
            try {
                //判断是不是中文字符
                if(Character.toString(chinese.toCharArray()[i]).matches("[\\u4E00-\\u9FA5]+")){
                    pyBuilder.append(PinyinHelper.toHanyuPinyinStringArray(chinese.toCharArray()[i],format)[0]);
                }else{
                    pyBuilder.append(chinese.toCharArray()[i]);
                }
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }
        }

        return pyBuilder.toString();
    }
}
