package org.nop.fastparse.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.nop.fastparse.entity.Table;

import java.util.List;
import java.util.Map;

public interface TableMapper extends BaseMapper<Table> {
    @Select("show tables")
    List<String> listTable();

    @Select("select COLUMN_NAME,DATA_TYPE from information_schema.columns where TABLE_NAME=#{tableName}")
    List<Map<String,String>> listTableColumn(String tableName);
}
