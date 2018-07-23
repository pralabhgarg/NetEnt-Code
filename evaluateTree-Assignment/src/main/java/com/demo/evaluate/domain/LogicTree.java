package com.demo.evaluate.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Representation of the LogicTree table.  Returning the Logic Tree as a XML string within the object - i.e. it has
 * not been digested and built into a "LogicTree" object
 */
@Data
@Entity(name = "logictree")
@Table(name = "LogicTree")
@ApiModel(value = "Logic Tree", description = "Logic Tree Resource representation")
@XmlRootElement
public class LogicTree implements Serializable {

    private static final long serialVersionUID = 3516729419965348909L;

    @ApiModelProperty(value = "logictreeuid", required = true)
    @Id
    @Column(name = "logictreeuid")
    private Integer logicTreeUID;

    @ApiModelProperty(value = "pack")
    @Column(name = "pack")
    private Integer pack;

    @ApiModelProperty(value = "code", required = true)
    @Column(name = "code", length = 20)
    private String code;

    @ApiModelProperty(value = "packCode", required = true)
    @Column(name = "packCode", length = 20)
    private String packCode;

    @ApiModelProperty(value = "languagecode", required = true)
    @Column(name = "languagecode", columnDefinition = "char", length = 2)
    private String languageCode;

    @ApiModelProperty(value = "logictree", required = true)
    @Lob
    @Column(name = "logictree")
    private String logicTreeXML;

}
