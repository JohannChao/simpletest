package com.johann.algorithm;

/**
 * @ClassName: KaratsubaBigNumber
 * @Description: 大数乘积
 * @Author: Johann
 * @Date: 2021-06-16 10:36
 **/
public class KaratsubaBigNumber {

    /**
     *  X * Y = Z
     *  X = x1*10^m + x2
     *  Y = y1*10^m + y2
     *
     *  Z = X * Y
     *    = (x1*10^m + x2) * (y1*10^m + y2)
     *    = (x1*y1)*10^2m + (x1*y2 + x2*y1)*10^m + x2*y2
     *
     *  Z = z1*10^2m + z2*10^m + z3
     *
     *  z1 = x1*y1
     *  z2 = x1*y2 + x2*y1
     *     = (x1 + x2)*(y1 + y2) - z1 - z3
     *     = (x1 + x2)*(y1 + y2) - x1*y1 - x2*y2
     *  z3 = x2*y2
     *
     *  时间复杂度：3n^log3 ≈ 3n^1.585
     *
     *
    */

}
