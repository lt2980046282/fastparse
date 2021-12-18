package ${generator.basePackage}.${generator.type};
import lombok.Data;
import org.springframework.stereotype.Component;
<#if generator.packages?exists>
    <#list generator.packages as value>
${value};
    </#list>
</#if>

@Data
@Component
public class ${generator.name} {
    <#list generator.params as value>
       private ${value}
    </#list>
}
