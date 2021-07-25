package com.hdc.seckill.utils;

import java.util.UUID;

/**
 * @ClassName UUIDUtil
 * @Description create uuid
 * @Author prog_evil
 * @Date 2021/7/24 9:51 下午
 * @Version 1.0
 **/
public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
