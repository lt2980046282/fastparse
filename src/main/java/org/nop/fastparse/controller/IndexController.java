package org.nop.fastparse.controller;

import com.alibaba.fastjson.JSON;
import org.nop.fastparse.config.FastGeneratorService;
import org.nop.fastparse.service.impl.FileServiceImpl;
import org.nop.fastparse.service.impl.JSONParserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
@RestController
public class IndexController {
    @Autowired
    FileServiceImpl fileService;
    @Autowired
    JSONParserServiceImpl parserService;
    @Autowired
    FastGeneratorService fastGeneratorService;
    List<String> entityList = new ArrayList<>();
    Map objectList = new HashMap();
    @PostMapping("/")
    public Map index(@RequestBody String msg) throws Exception {
        objectList = fastGeneratorService.parse(msg);
        System.out.println(objectList);
        return objectList;
    }




}
