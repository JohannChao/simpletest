package com.johann.stringTest;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName: StringCodeTest
 * @Description: TODO
 * @Author: Johann
 * @Date: 2021-07-27 11:32
 **/
public class StringCodeTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "/program/xydocbase/ReportExportFile\\Information_SQL\\20210727\\&auml;&iquest;&iexcl;&aelig;\u0081&macr;&aring;\u008C&sup1;&eacute;\u0085\u008D_20210727112957.xlsx";
        byte[] utf8Bytes = str.getBytes("ISO-8859-1");

        String s11 = new String(str.getBytes("ISO-8859-1"), "ISO-8859-1");
        String s12 = new String(str.getBytes("ISO-8859-1"), "GB2312");
        String s13 = new String(str.getBytes("ISO-8859-1"), "UTF-8");
        String s14 = new String(str.getBytes("ISO-8859-1"), "GBK");

        String s15 = new String(str.getBytes("UTF-8"), "ISO-8859-1");

        String s16 = new String(str.getBytes("UTF-8"), "UTF-8");
        String s17 = new String(str.getBytes("UTF-8"), "ISO-8859-1");

    }
}
