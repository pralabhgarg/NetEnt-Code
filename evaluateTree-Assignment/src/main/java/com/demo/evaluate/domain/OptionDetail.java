package com.demo.evaluate.domain;

import java.io.Serializable;
import java.util.Set;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Option Details
 */
@Data
public class OptionDetail implements Serializable {

    private static final long serialVersionUID = -8341302661450354252L;

    /**
     * Description
     */
    private String description = "";

    /**
     * Extended description
     */
    private String extendedDescription;

    /**
     * MSRP
     */
    private Float msrp;

    /**
     * Invoice price
     */
    private Float invoice;
    
    
    @ApiModelProperty(value = "A collection of option codes that is some way this option is associated with (either owns/owned by/paired with).")
    private Set<String> associations;

}
