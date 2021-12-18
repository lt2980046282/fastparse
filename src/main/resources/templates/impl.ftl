package ${generator.basePackage}.${generator.type};
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ${generator.basePackage}.entity.${generator.name};
import ${generator.basePackage}.mapper.${generator.name}Mapper;
import ${generator.basePackage}.service.${generator.name}Service;

import java.util.List;
import java.util.Map;

@Service
public class  ${generator.name}${generator.javaType} extends ServiceImpl< ${generator.name}Mapper, ${generator.name}> implements  ${generator.name}Service {

}
