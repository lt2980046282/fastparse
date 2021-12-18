package org.nop.fastparse.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.nop.fastparse.service.impl.FastGeneratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@NoArgsConstructor
@Service
@Data
public class FastGeneratorService {
    private String basePackage;
    public String name;

    @Autowired
    FastGeneratorServiceImpl service;



    public Map parse(String msg) throws Exception {
        return service.parseJSONtoDTO(msg);
    }

}
