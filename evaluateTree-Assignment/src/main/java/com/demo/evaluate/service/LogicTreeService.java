package com.demo.evaluate.service;

import java.util.List;

import com.demo.evaluate.domain.LogicTree;

/**
 * Logic Tree Service interface for LogicTree and contains
 * method to fetch Catalog Option Details.
 */
@FunctionalInterface
public interface LogicTreeService {

    /**
     * @param lngCode is the language code
     * @param ccode   is the 16 character alphanumeric code
     * @param llp     is the lower level package associated with that vehicle
     * @return returns a list of catalog options in the form of a logic tree
     */
    List<LogicTree> findByCcodeAndLlpCodeAndLanguageCode(String ccode, String llp, String lngCode);

}
