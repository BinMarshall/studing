package com.vue2.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：
 * 作者姓名： Hao Bin - marshall
 * 创建时间：2018/8/19 1:36
 */
@RestController
@RequestMapping("/vue")
public class VueController {
    ObjectMapper mapper = new ObjectMapper();
    @RequestMapping(value="/test1",produces = "application/json;charset=UTF-8")
    public String test1(HttpServletRequest request) throws JsonProcessingException {
        Map<String, Object> mat = new HashMap<>();
        mat.put("A", request.getParameter("a"));
        mat.put("B", request.getParameter("b"));
        String result = mapper.writeValueAsString(mat);
        String callback = request.getParameter("callback123");
        result = callback + "(" + result + ")";
        return result;
    }
}
