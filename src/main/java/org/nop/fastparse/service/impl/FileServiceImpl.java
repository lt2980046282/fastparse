package org.nop.fastparse.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FileServiceImpl {
    List<String> list= new ArrayList<>();
    public List<String> getEntity(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        getEntity(file2.getAbsolutePath());
                    } else {
                        list.add(file2.getName());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return list;
    }
    //判断文件存在逻辑
    public boolean getEntity(String path, String fileName) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        return getEntity(file2.getAbsolutePath(),fileName);
                    } else {
                        return true;
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
            return false;
        }
        return false;
    }
    public boolean isExistFile(String fileName) {
        boolean isExistEntity = false;
        for (String localEntity : list) {
            if (fileName.equals(localEntity)) {
                System.out.println(fileName + "实体类存在");
                isExistEntity = true;
                break;
            }
        }
        return isExistEntity;
    }
}
