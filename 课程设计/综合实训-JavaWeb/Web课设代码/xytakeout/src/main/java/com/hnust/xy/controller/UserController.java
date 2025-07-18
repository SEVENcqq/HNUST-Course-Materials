package com.hnust.xy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hnust.xy.common.R;
import com.hnust.xy.entity.User;
import com.hnust.xy.service.UserService;
import com.hnust.xy.utils.SMSUtils;
import com.hnust.xy.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * ClassName: UserController
 * Package: com.hnust.xy.controller
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/25 - 15:10
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 发送手机短信验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        //获取手机号
        String phone = user.getPhone();

        if(StringUtils.isNotEmpty(phone)){
            //生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("生成的验证码为：{}",code);

            //调用阿里云提供的短信服务API完成发送短信
            //SMSUtils.sendMessage("戏言外卖","SMS_461085217",phone,code);
            //SMSUtils.sendMessage("阿里云短信测试","SMS_154950909",phone,code);

            //需要将生成的验证码保存到Session
            session.setAttribute(phone,code);

//            return R.success("手机验证码短信发送成功");
            return R.success("手机验证码为：" + code);

        }
        return R.error("手机验证码短信发送失败");
    }


    /**
     * 移动端用户登录
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map<String,String> map, HttpSession session){
        log.info(map.toString());

        //获取手机号
        String phone = map.get("phone").toString();

        //获取验证码
        String code = map.get("code").toString();

        //从Session中获取保存的验证码
        Object codeInSession = session.getAttribute(phone);

        //进行验证码的比对（页面提交的验证码和Session中保存的验证码比对）
        if(codeInSession != null && codeInSession.equals(code)){
            //如果能够比对成功，说明登录成功
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);

            User user = userService.getOne(queryWrapper);

            //判断当前手机对应的用户是否为新用户，如果是新用户就自动完成注册
            if(user == null){
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }

            session.setAttribute("userName",user.getName());
            session.setAttribute("user",user.getId());
            return R.success(user);
        }

        return R.error("登录失败");
    }

    /**
     * 退出功能
     * @param request
     * @return
     */
    @PostMapping("/loginout")
    public R<String> loginout(HttpServletRequest request){
        //清理session中的用户id
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }

}
