package com.demo.evaluate.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * Error response class so that we can provide both an XML and JSON response.
 * <p>
 * '@Data' will consider this class as a POJo and take care of getters/setters.
 * '@XmlRootElement' represents the value of this object as XML element in an XML document.
 * '@XmlAccessorType' provides control over the default serialization of properties and fields in a class.
 * XmlAccessType.FIELD - Every non static, non transient field in a JAXB-bound class will be automatically bound to
 * XML, unless annotated by XmlTransient. Getter/setter pairs are bound to XML only when they are explicitly annotated
 * by some of the JAXB annotations.
 */
@Data
@XmlRootElement(name = "ErrorResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorResponse {
    @XmlElement
    private Long timestamp;
    @XmlElement
    private Integer status;
    @XmlElement
    private String error;
    @XmlElement
    private String message;
    @XmlElement
    private String path;

    /**
     * Constructor is required for libraries that indirectly instantiate the class
     */
    public ErrorResponse() {
    }

    /**
     * Parameterized Constructor is required for classes that instantiate this class passing values for fields
     * @param status status msg
     * @param error error msg
     * @param message message
     * @param path URI
     */
    public ErrorResponse(Integer status, String error, String message, String path) {
        this.timestamp = System.currentTimeMillis();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
