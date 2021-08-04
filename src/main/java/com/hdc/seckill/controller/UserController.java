package com.hdc.seckill.controller;


import com.hdc.seckill.pojo.User;
import com.hdc.seckill.vo.RespBean;
import com.hdc.seckill.vo.RespBeanEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hdc
 * @since 2021-07-18
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /*
     * @Author ProG_Evil
     * @Description //用户信息（测试）
     * @Date 10:48 上午 2021/8/4
     * @Param
     * @return
     **/
    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user){
        return RespBean.success(user);
    }
}
