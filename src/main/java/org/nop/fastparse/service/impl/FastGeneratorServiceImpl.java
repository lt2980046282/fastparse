package org.nop.fastparse.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.nop.fastparse.entity.Generator;
import org.nop.fastparse.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class FastGeneratorServiceImpl implements GeneratorService {
    @Autowired
    private JSONParserServiceImpl jsonParserService;

    //解析一级对象
    public Map parseJSONtoDTO(String msg) throws Exception {
        return jsonParserService.init(msg);
    }
}
