package com.congmai.zhgj.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName CommonController
 * @Description 公共视图控制器
 * @author tanzb
 * @Date 2017年7月26日 下午2:42:47
 * @version 1.0.0
 */
@Controller
public class CommonController {
    /**
     * 首页
     * 
     * @param request
     * @return
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        return "index";
    }

}