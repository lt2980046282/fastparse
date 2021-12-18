package org.nop.fastparse.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.util.Map;

@Slf4j
public class BaseProcess {

    @Autowired
    private Configuration configuration;

    /***
     * 生成静态页
     * @param dataMap
     *          dataMap.templateName: 模板名字
     *          dataMap.path: 生成文件存储路径
     *          dataMap.name: 生成的文件名字
     * @throws Exception
     */
    public void writerPage(Map<String, Object> dataMap) throws Exception {
        //获取模板名字
        String templateName = dataMap.get("templateName").toString();

        //文件生存的路径
        String path = dataMap.get("path").toString();

        //文件路径如果不存在，则创建
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        //获取文件名字
        String fileName = dataMap.get("name").toString();



        try {
            Template template = configuration.getTemplate(templateName);
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, dataMap);
            FileUtils.writeStringToFile(new File(path, fileName), content);

        } catch (TemplateNotFoundException e) {
            log.info("模板"+templateName+"不存在，请手动建立模板");
        }
    }
}