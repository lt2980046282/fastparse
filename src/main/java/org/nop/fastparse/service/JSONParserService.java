package org.nop.fastparse.service;

import org.nop.fastparse.entity.Generator;

import java.util.Map;

public interface JSONParserService {
    void parseToClass() throws Exception;
    void  parseToJava(Generator generator) throws Exception;
    Map init(String msg) throws Exception;
    void parseObject() throws Exception;
    void parse(Object o,String keyName, int typeCode) throws Exception;
}
