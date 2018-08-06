package com.example.demo;

import com.example.service.TestService;
import org.aspectj.weaver.ast.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controllers {

    @Resource
    private TestService testService;

    @RequestMapping("/aaa")
    public void aaa(String a,String b){

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("key1", "v1");
            params.put("key2", "v2");

//        testService.insert(params, "000");
//        testService.update("name", "id");
//        testService.delete("leftso");
            boolean b1 = testService.doError("leftso.com");

        }catch (Exception e){
            System.out.println("恩打印完了");
        }
    }

}
