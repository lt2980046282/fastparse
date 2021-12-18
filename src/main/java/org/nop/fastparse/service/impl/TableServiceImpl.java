package org.nop.fastparse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nop.fastparse.entity.Table;
import org.nop.fastparse.mapper.TableMapper;
import org.nop.fastparse.service.TableService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TableServiceImpl extends ServiceImpl<TableMapper, Table> implements TableService {

    @Override
    public List<String> showTable() {
        return this.baseMapper.listTable();
    }

    @Override
    public List<Map<String,String>> showField(String tableName) {
        return this.baseMapper.listTableColumn(tableName);
    }
}
