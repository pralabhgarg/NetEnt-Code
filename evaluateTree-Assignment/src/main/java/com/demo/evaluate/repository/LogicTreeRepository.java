package com.demo.evaluate.repository;


import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.evaluate.domain.LogicTree;

/**
 * JPA repositories are only Interfaces.  There is no implementation of the repository.  JPA and Hibernate do their
 * magic to build the code needed to execute a query and map to/from entities on the fly.
 */
public interface LogicTreeRepository extends JpaRepository<LogicTree, Integer> {

    String QUERY = "select lt.LogicTreeUID as logictreeuid, lt.LowerLevelPackageUID as lowerLevelPackageUID, "
            + "lt.CCode as ccode, lt.LLPCode as llpCode, lt.LanguageCode as languageCode, lt.LogicTree as logictree "
            + "from LogicTree lt where lt.CCode = :ccode and lt.LLPCode = :llpCode and lt.LanguageCode = :languageCode ";

    @Cacheable(cacheNames = "catalog-option", keyGenerator = "catalogOptionKeyGenerator",
            unless = "#result != null and #result.size() == 0")
    List<LogicTree> findByCodeAndPackAndLanguageCodeIgnoreCase(
            String ccode, String llpCode, String languageCode);

    @Cacheable(cacheNames = "catalog-option", keyGenerator = "catalogOptionKeyGenerator",
            unless = "#result != null and #result.size() == 0")
    @Query(value = QUERY, nativeQuery = true)
    List<LogicTree> findByCodeAndPackAndLanguageCodeIgnoreCaseAndReleaseLevel(
            @Param("code") String ccode, @Param("pack") String llpCode, @Param("languageCode") String languageCode, @Param("releaseLevel") Integer releaseLevel);
}
