package ${generator.basePackage}.${generator.type};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ${generator.basePackage}.service.impl.${generator.name}ServiceImpl;
import ${generator.basePackage}.entity.${generator.name};
import org.springframework.web.bind.annotation.${generator.funType}Mapping;
@RestController
public class ${generator.name}${generator.javaType} {
    @Autowired
    ${generator.name}${generator.javaType} generatorService;

    @Autowired
    ${generator.name} generator;

    @${generator.funType}Mapping("/")
    public String index(${generator.funParam}){
        return null;
    }
}
