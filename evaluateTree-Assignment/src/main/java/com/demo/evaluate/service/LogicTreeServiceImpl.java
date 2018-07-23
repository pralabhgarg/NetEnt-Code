package com.demo.evaluate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.demo.evaluate.domain.LogicTree;
import com.demo.evaluate.repository.LogicTreeRepository;
import com.demo.evaluate.util.CatalogOptionUtil;

/**
 * LogicTreeServiceImpl is the service provider for LogicTree and contains
 * method to fetch Catalog Option Details. It uses application configuration to
 * identify configured data mode and release level.
 *
 */
@Service
public class LogicTreeServiceImpl implements LogicTreeService {

    @Autowired
    private LogicTreeRepository logicTreeRepository;

    @Value("${catalogoption.releaselevel:-1}")
    private Integer releaseLevel;

    @Value("${catalogoption.datamode:runtime}")
    private String dataMode;

    @Override
    public List<LogicTree> findByCcodeAndLlpCodeAndLanguageCode(String ccode, String llp, String lngCode) {
        if(CatalogOptionUtil.isTopMaster(dataMode)) {
            return logicTreeRepository.findByCodeAndPackAndLanguageCodeIgnoreCaseAndReleaseLevel(ccode, llp, lngCode, releaseLevel);
        }
        return logicTreeRepository.findByCodeAndPackAndLanguageCodeIgnoreCase(ccode, llp, lngCode);
    }
}
