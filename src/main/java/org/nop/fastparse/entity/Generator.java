package org.nop.fastparse.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Component
@Data
@ConfigurationProperties(prefix = "fastparse")
public class Generator<T> implements Serializable {
    //生成的类型名称
    private String type = "";
    //基础包列表
    private List<String> basePackageDir;
    //controller层接口名称
    private String funName;
    //controller层接口类型 get、post、request
    private String funType;
    //controller请求参数
    private List< Object> funParams;

    private String path;


    private String funParam;

    //controller请求风格
    private String reqStyle;
    //controller生成类属性
    private List<String> params;

    private String param;

    private List<String> packages;

    private String templateName;

    private T data;

    private String template;

    private String javaType;


    public static final String DEFAULT_BASEPACKAGENAME = "com";
    public static final String DEFAULT_NAME = "index";
    private String basePackage = DEFAULT_BASEPACKAGENAME;
    private String name = DEFAULT_NAME;

}
