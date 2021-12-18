package ${generator.basePackage}.${generator.type};
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Component;
<#list generator.packageEntity as value>
import ${value};
</#list>
@Data
@Component
public class ${generator.name} {
    <#list generator.params as value>
        ${value}
    </#list>
}
