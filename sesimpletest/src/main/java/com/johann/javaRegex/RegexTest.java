package main.java.com.johann.javaRegex;

import java.util.regex.Pattern;

/**
 * @ClassName RegexTest
 * @Description TODO
 * @Author Johann
 * @Date 2019-12-3 15:57
 **/
public class RegexTest {

    public static void main(String[] args) {
        //匹配一个数字
        String str = "0";
        String regexStr = "[0-9]"; //匹配一次 0-9 的数字
        boolean flag1 = Pattern.matches(regexStr,str);
        System.out.println("flag1 : "+flag1);

        //匹配随机的数字
        str = "800";
        regexStr = "[0-9]*";//零次或多次 [0-9]{0,}
        boolean flag2 = Pattern.matches(regexStr,str);
        System.out.println("flag2 : "+flag2);

        regexStr = "[0-9]+";//一次或多次 [0-9]{1,}
        boolean flag3 = Pattern.matches(regexStr,str);
        System.out.println("flag3 : "+flag3);

        regexStr = "[0-9]?";//零次或一次 [0-9]{0,1}
        boolean flag4 = Pattern.matches(regexStr,str);
        System.out.println("flag4 : "+flag4);

        //匹配随机的字母
        str = "CBD";

        regexStr = "[A-Z]*";// 大写字母零次或多次 [A-Z]{0,}
        boolean flag5 = Pattern.matches(regexStr,str);
        System.out.println("flag5 : "+flag5);

        regexStr = "[A-Z]{1,3}";// 大写字母1-3次
        boolean flag6 = Pattern.matches(regexStr,str);
        System.out.println("flag6 : "+flag6);

        regexStr = "[a-z]{1,3}";// 小写字母1-3次
        boolean flag7 = Pattern.matches(regexStr,str);
        System.out.println("flag7 : "+flag7);

        //匹配手机号 130-139 150-159 180-189 177 后面8位随机
        // 要匹配这种类型的手机号 第一部分是 1,最后一部分是 \d{8}
        //中间两位可能是 3[0-9] 5[0-9] 8[0-9] 77,即中间这部分的正则表达式是 (3[0-9]|5[0-9]|8[0-9]|77)
        //最终的正则表达式是 1(3[0-9]|5[0-9]|8[0-9]|77)\d{8}
        //来试一下
        regexStr = "1(3[0-9]|5[0-9]|8[0-9]|77)\\d{8}";
        for(int i=0;i<=9;i++){
            String sjh = "13"+i+"33333333";
            boolean flag = Pattern.matches(regexStr,sjh);
            System.out.println(sjh+" : "+flag);
        }
        for(int i=0;i<=9;i++){
            String sjh = "15"+i+"55555555";
            boolean flag = Pattern.matches(regexStr,sjh);
            System.out.println(sjh+" : "+flag);
        }
        for(int i=0;i<=9;i++){
            String sjh = "18"+i+"99999999";
            boolean flag = Pattern.matches(regexStr,sjh);
            System.out.println(sjh+" : "+flag);
        }
        String sjh = "17800000000";
        boolean flag = Pattern.matches(regexStr,sjh);
        System.out.println(sjh+" : "+flag);


        // 替换字符
        String str1 = "aa12aa00D00ZZ14ZZ";
        //将 str1 中的所有大写字母(单个或多个相连的)替换为 #
        String regexStr1 = "[A-Z]+";
        String str11 = str1.replaceAll(regexStr1,"#");
        System.out.println(str1);
        System.out.println(str11);

        // [A-Z]+ 更换为 [A-Z]
        regexStr1 = "[A-Z]";
        str11 = str1.replaceAll(regexStr1,"#");
        System.out.println(str1);
        System.out.println(str11);

        //将 str1 中的所有小写字母(单个)替换为 *
        regexStr1 = "[a-z]";
        str11 = str1.replaceAll(regexStr1,"*");
        System.out.println(str1);
        System.out.println(str11);

        //将 str1 中的所有数字(单个或多个相连的)替换为 @
        regexStr1 = "\\d{1,}";
        str11 = str1.replaceAll(regexStr1,"@");
        System.out.println(str1);
        System.out.println(str11);

        //切割字符
        String str2 = "123ABC C132C21DD22";
        //按照大写字母切割字符串

        //按照大写字母(单个或者多个相连的字母视为一组)切割字符串
        String regexStr2 = "[A-Z]+";
        String [] strArray  = str2.split(regexStr2);
        System.out.println("strArray.length : "+strArray.length);
        for(String s : strArray){
            System.out.println(s);
        }

        //按照大写字母(单个)切割字符串
        regexStr2 = "[A-Z]";
        strArray  = str2.split(regexStr2);
        System.out.println("strArray.length : "+strArray.length);
        for(String s : strArray){
            System.out.println(s);
        }
    }
}
