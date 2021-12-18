package org.nop.fastparse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.nop.fastparse.entity.Table;

import java.util.List;
import java.util.Map;

public interface TableService extends IService<Table>   {
    public List<String> showTable();
    public List<Map<String,String>> showField(String tableName);
}
