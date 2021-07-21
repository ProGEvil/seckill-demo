package com.hdc.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName MD5Util
 * @Description double MD5
 * @Author prog_evil
 * @Date 2021/7/17 3:23 下午
 * @Version 1.0
 **/
@Component
public class MD5Util {

    public static String md5(String arc){
        return DigestUtils.md5Hex(arc);
    }

    //和前端的salt统一
    private static final String salt = "1a2b3c4d";

    //第一次加密 前端输入的密码转成一个后端接受的密码
    //该方法为前端处理方法
    public static String inputPassToFromPass(String inputPass){
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    //第二次加密 后端的密码转到数据库的密码
    public static String formPassToDBPass(String formPass,String salt){//salt为存到数据库
        //该salt为随机生成的
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    //该方法为后端调用的方法
    public static String  inputPassToDBPass(String inputPass,String salt){//salt为随机生成的
        //两次传递
        String fromPass = inputPassToFromPass(inputPass);
        String dbPass = formPassToDBPass(fromPass, salt);
        return dbPass;//真正需要的返回值
    }

    //test
    public static void main(String[] args) {
        //ce21b747de5af71ab5c2e20ff0a60eea
        System.out.println(inputPassToFromPass("123456"));
        //1897a69ef451f0991bb85c6e7c35aa31
        System.out.println(formPassToDBPass("d3b1294a61a07da9b49b6e22b2cbd7f9","1a2b3c4d"));
        //1897a69ef451f0991bb85c6e7c35aa31
        System.out.println(inputPassToDBPass("123456","1a2b3c4d"));
    }
}
