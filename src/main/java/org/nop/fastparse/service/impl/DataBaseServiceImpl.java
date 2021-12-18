package org.nop.fastparse.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DataBaseServiceImpl {

    @Autowired
    private TableServiceImpl tableService;

    //数据库表必存在一条数据

    //获取数据表名
    List<String> getTable() {
        return tableService.showTable();
    }
    //获取数据库列名及类型
    public List<Map<String, String>> getFields(String table) {
        return this.tableService.showField(table);
    }
}
