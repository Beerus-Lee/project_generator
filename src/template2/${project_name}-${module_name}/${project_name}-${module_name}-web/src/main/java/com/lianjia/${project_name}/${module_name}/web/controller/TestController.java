package com.lianjia.${project_name}.${module_name}.web.controller;

import com.lianjia.${project_name}.${module_name}.common.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/api/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult index() {
        return JsonResult.successResult("hello");
    }


}
