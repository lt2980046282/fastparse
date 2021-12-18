package org.nop.fastparse.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.nop.fastparse.entity.Generator;
import org.nop.fastparse.service.PageService;
import org.nop.fastparse.util.BaseProcess;
import org.nop.fastparse.util.GenStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class PageServiceImpl extends BaseProcess implements PageService {
    String path = System.getProperty("user.dir") + "\\src\\main\\java\\";
    List<String> packageList = Arrays.asList(new String[]{"entity", "service", "controller", "serviceimpl"});

    @Autowired
    Generator generator;
    @Override
    public void itemPage(Map<String, Object> dataMap) throws Exception {
        super.writerPage(dataMap);
    }


}
