package org.nop.fastparse.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.nop.fastparse.entity.Generator;
import org.nop.fastparse.entity.Type;
import org.nop.fastparse.service.JSONParserService;
import org.nop.fastparse.util.GenStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Service

public class JSONParserServiceImpl implements JSONParserService {
    @Autowired
    Generator generator;
    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    DataBaseServiceImpl dataBaseService;
    @Autowired
    PageServiceImpl pageService;
    String path = System.getProperty("user.dir") + "\\src\\main\\java\\";
    Map objectList = new HashMap();

    @Override
    public Map init(String msg) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(msg);
        //获取基础包路径
        String basePackage = jsonObject.getString("basePackage");

        //接口函数名称
        String funName = jsonObject.getString("funName");
        //请求类型
        String funType = jsonObject.getString("funType");
        //生成接口的请求参数
        Object funParamsList = jsonObject.get("funParams");
        parseToController((Map) funParamsList);
        //接口风格
        String reqStyle = jsonObject.getString("reqStyle");
        //获取接口json返回值
        Object data = jsonObject.getJSONObject("data");
        //如果dto存在则不生成
        if (!StringUtils.isEmpty(basePackage)) {
            generator.setBasePackage(basePackage);
        }
        generator.setFunName(funName);
        generator.setFunType(GenStringUtils.captureName(funType));
        generator.setReqStyle(reqStyle);
//        generator.setBasePackageDir(packageList);
//        generator.setBasePackage(basePackage);
        //解析data数据
        parse(data, "root",0);
        parseObject();
        return objectList;
    }

    /**
     * 递归迭代所有json
     *
     * @param object
     * @param keyName
     * @return
     */
    @Override
    public void parse(Object object, String keyName, int typeCode) throws Exception {
        String key = keyName;
        if (object instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) object;
            Set<Map.Entry<String, Object>> set = map.entrySet();
//            System.out.println(object + "是对象");
            //统计对象个数逻辑
            //构建列表
            List<Map> list = new ArrayList<>();
            for (Map.Entry entry : set) {
                key = (String) entry.getKey();
                list.add(Type.checkType(entry.getValue(), key, 0));
                objectList.put(keyName, list);
                parse(entry.getValue(), key, typeCode);
            }
        }
        if (object instanceof List) {
            for (Object ls : (List) object) {
                parse(ls, key, typeCode);
            }
        }

        if (ObjectUtils.isEmpty(object)) {
            //判断是否存在该entity，存在则不执行
            //获取数据库表列表
            List<String> tables = dataBaseService.getTable();
            boolean isExistTable = false;
            String table = null;
            for (String tableName : tables) {
                if (tableName.contains(key)) {
                    isExistTable = true;
                    table = tableName;
                    break;
                }
            }
            List list1 = new ArrayList();
            if (isExistTable) {
                System.out.println("存在匹配表名" + key);
                //数据库表解析逻辑

                List<Map<String, String>> fields = dataBaseService.getFields(table);
                for (Map map1 : fields) {
                    Map map2 = Type.checkSQLType(map1.get("DATA_TYPE"), map1.get("COLUMN_NAME"));
                    list1.add(map2);
                }
            }
            objectList.put(keyName, list1);
        }

    }

    @Override
    public void parseObject() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        Set<Map.Entry<String, Object>> set = objectList.entrySet();
        //获取className
        for (Map.Entry entry : set) {
            String key = (String) entry.getKey();
            //获取class属性
            List<Map> list = (List<Map>) entry.getValue();
            String entity = GenStringUtils.captureName(key);
            //遍历属性列表
            List<String> packages = new ArrayList<>();
            List<String> params = new ArrayList<>();

            //获取需要生成的包路径
            String basePackage = generator.getBasePackage();
            String typeName = "entity";
            for (Map param : list) {
                params.add((String) param.get("param"));
                if (!StringUtils.isEmpty(param.get("package"))) {
                    packages.add((String) param.get("package"));
                }
                if (!StringUtils.isEmpty(param.get("baseEntity"))) {
                    packages.add("import " + basePackage + "." + typeName + "." + GenStringUtils.captureName((String) param.get("baseEntity")) + ";");
                }
            }
            generator.setName(GenStringUtils.captureName(key));
            generator.setParams(params);
            generator.setPackages(packages);
            generator.setBasePackage(basePackage);
            generator.setType("entity");
            generator.setPath(path + basePackage.replace(".", "\\"));
            parseToJava(generator);
        }
    }

    @Override
    public void parseToJava(Generator generator) throws Exception {
        List<String> list = new ArrayList<>();
        if (!ObjectUtils.isEmpty(generator.getBasePackageDir())) {
            list = generator.getBasePackageDir();
        }
        list.add("entity");
        list.add("mapper");
        list.add("service");
        list.add("impl");
        list.add("controller");
        String packagePath = generator.getPath();
        for (String packageName : list) {
            if (packageName.equals("entity")) {
                generator.setPath(packagePath + "\\entity");
                generator.setTemplateName("entity1.ftl");
                generator.setType("entity");
                generator.setJavaType("");
            }
            if (packageName.equals("mapper")) {
                generator.setPath(packagePath + "\\mapper");
                generator.setType("mapper");
                generator.setJavaType("Mapper");
                generator.setTemplateName("mapper.ftl");

            }
            if (packageName.equals("service")) {
                generator.setPath(packagePath + "\\service");
                generator.setType("service");
                generator.setJavaType("Service");
                generator.setTemplateName("service.ftl");

            }
            if (packageName.equals("impl")) {
                generator.setPath(packagePath + "\\service\\impl");
                generator.setType("service.impl");
                generator.setJavaType("ServiceImpl");
                generator.setTemplateName("impl.ftl");

            }
            if (packageName.equals("controller")) {
                generator.setPath(packagePath + "\\controller");
                generator.setJavaType("Controller");
                generator.setType("controller");
                generator.setTemplateName("controller.ftl");

            }
            parseToClass();
        }
    }

    @Override
    public void parseToClass() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        String fileName = generator.getName() + generator.getJavaType() + ".java";
        if (!fileService.isExistFile(fileName)) {
            dataMap.put("name", fileName); //生成静态页的文件名字
            dataMap.put("templateName", generator.getTemplateName()); //模板名字
            dataMap.put("generator", generator);
            dataMap.put("path", generator.getPath()); //文件路径
            pageService.itemPage(dataMap);
        }
    }

    public void parseToController(Map object) throws Exception {
        Set<Map.Entry<String,String>> set = object.entrySet();

        String param = "";
        for (Map.Entry<String,String> params : set) {
            param += Type.checkType(params.getValue(),params.getKey(),1).get("param")+", ";
        }
        param = GenStringUtils.replaceDot(param);
        generator.setFunParam(param);
        System.out.println(param);

    }
}
