package ${generator.basePackage}.${generator.type};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ${generator.basePackage}.entity.${generator.name};
import java.util.List;
import java.util.Map;

public interface ${generator.name}${generator.javaType} extends BaseMapper<${generator.name}> {

}
