package com.kevin;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by min on 2017/5/26.
 */
public class Util {

    // 判断token是否过期
    public boolean checkToken (String token){

        Long lastLoginTime = Long.parseLong(token.substring(0,11));
        long l = System.currentTimeMillis();
        String strLong = Long.toString(l);
        Long nowTime = Long.parseLong(strLong.substring(0,11));

        // 过期时间3600秒
        return nowTime - lastLoginTime <= 3600;



    }
}
