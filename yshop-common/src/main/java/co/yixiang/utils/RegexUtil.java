package co.yixiang.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：LionCity
 * @date ：Created in 2020-10-19 14:44
 * @description：正则表达式匹配两个字符串之间的内容
 * @modified By：
 * @version:
 */
public class RegexUtil {


    /**
     * 转换产品描述
     *
     * @param str str
     * @return {@link String}
     */
    public static String converProductDescription(String str){
       StringBuilder sb = new StringBuilder();
       List<String> imgArr=Arrays.asList(str.split("p><img"));
       for(int i=0;i<imgArr.size();i++){
           String img=imgArr.get(i);
           if(i!=imgArr.size()-1){
               img=img+"p><img";
           }
           if(img.indexOf("src")>0){
               if(img.indexOf("style=")>0){
                   String rgex = "style=\"(.*?)\"";
                   String rgexStr = getSubUtilSimple(img, rgex);
                   if(rgexStr.indexOf("max-width: 100%;")<0){
                       img=img.replace( rgexStr ,rgexStr+"max-width: 100%");
                   }
               }else{
                   img=" style=\"max-width: 100%;\""+img;
               }
           }
           sb.append(img);
       }
       return sb.toString();
   }


    /**
     * 正则表达式匹配两个指定字符串中间的内容
     *
     * @param soap
     * @return
     */
    public static List<String> getSubUtil(String soap, String rgex) {
        List<String> list = new ArrayList<String>();
        // 匹配的模式
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        return list;
    }

    /**
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
     *
     * @param soap
     * @param rgex
     * @return
     */
    public static String getSubUtilSimple(String soap, String rgex) {
        // 匹配的模式
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            return m.group(1);
        }
        return "";
    }
}