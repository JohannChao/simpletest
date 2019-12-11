
```text
\           将下一字符标记为特殊字符、文本、反向引用或八进制转义符。
^           匹配输入字符串开始的位置。
$           匹配输入字符串结尾的位置。
*           零次或多次匹配前面的字符或子表达式。例如，zo* 匹配"z"和"zoo"。* 等效于 {0,}。
+           一次或多次匹配前面的字符或子表达式。例如，"zo+"与"zo"和"zoo"匹配，但与"z"不匹配。+ 等效于 {1,}。
?           零次或一次匹配前面的字符或子表达式。例如，"do(es)?"匹配"do"或"does"中的"do"。? 等效于 {0,1}。
{n}         n 是非负整数。正好匹配 n 次。例如，"o{2}"与"Bob"中的"o"不匹配，但与"food"中的两个"o"匹配。
{n,}        n 是非负整数。至少匹配 n 次。例如，"o{2,}"不匹配"Bob"中的"o"，而匹配"foooood"中的所有 o。"o{1,}"等效于"o+"。"o{0,}"等效于"o*"。
{n,m}       m 和 n 是非负整数，其中 n <= m。匹配至少 n 次，至多 m 次。m 和 n 是非负整数，其中 n <= m。匹配至少 n 次，至多 m 次。
            注意：您不能将空格插入逗号和数字之间。
?           当此字符紧随任何其他限定符（*、+、?、{n}、{n,}、{n,m}）之后时，匹配模式是"非贪心的"。
            "非贪心的"模式匹配搜索到的、尽可能短的字符串，而默认的"贪心的"模式匹配搜索到的、尽可能长的字符串。
            例如，在字符串"oooo"中，"o+?"只匹配单个"o"，而"o+"匹配所有"o"。
.           匹配除"\r\n"之外的任何单个字符。若要匹配包括"\r\n"在内的任意字符，请使用诸如"[\s\S]"之类的模式。        
(pattern)   匹配 pattern 并捕获该匹配的子表达式。可以使用 $0…$9 属性从结果"匹配"集合中检索捕获的匹配。若要匹配括号字符 ( )，请使用"\("或者"\)"。
x|y         匹配 x 或 y。例如，'z|food' 匹配"z"或"food"。'(z|f)ood' 匹配"zood"或"food"。
[xyz]       字符集。匹配包含的任一字符。例如，"[abc]"匹配"plain"中的"a"。
[^xyz]      反向字符集。匹配未包含的任何字符。例如，"[^abc]"匹配"plain"中"p"，"l"，"i"，"n"。
[a-z]       字符范围。匹配指定范围内的任何字符。例如，"[a-z]"匹配"a"到"z"范围内的任何小写字母。
[^a-z]      反向范围字符。匹配不在指定的范围内的任何字符。例如，"[^a-z]"匹配任何不在"a"到"z"范围内的任何字符。
\b          匹配一个字边界，即字与空格间的位置。例如，"er\b"匹配"never"中的"er"，但不匹配"verb"中的"er"。
\B          非字边界匹配。"er\B"匹配"verb"中的"er"，但不匹配"never"中的"er"。
\cx         匹配 x 指示的控制字符。例如，\cM 匹配 Control-M 或回车符。x 的值必须在 A-Z 或 a-z 之间。如果不是这样，则假定 c 就是"c"字符本身。
\d          数字字符匹配。等效于 [0-9]。
\D          非数字字符匹配。等效于 [^0-9]。
\f          换页符匹配。等效于 \x0c 和 \cL。
\n          换行符匹配。等效于 \x0a 和 \cJ。
\r          匹配一个回车符。等效于 \x0d 和 \cM。
\t          制表符匹配。与 \x09 和 \cI 等效。
\v          垂直制表符匹配。与 \x0b 和 \cK 等效。
\s          匹配任何空白字符，包括空格、制表符、换页符等。与 [ \f\n\r\t\v] 等效。
\S          匹配任何非空白字符。与 [^ \f\n\r\t\v] 等效。
\w          匹配任何字类字符，包括下划线。与"[A-Za-z0-9_]"等效。
\W          与任何非单词字符匹配。与"[^A-Za-z0-9_]"等效。
```

```java
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

        /**********************************************************************************/

        Pattern pattern = Pattern.compile("a\\b");
        Matcher matcher = pattern.matcher("sa,");
        boolean matcherFlag = matcher.find();
        System.out.println("matcherFlag : "+matcherFlag);
    }
}
```