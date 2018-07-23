package com.demo.evaluate.service.configurator.vehicle.paints;

import java.io.Serializable;

/**
 * InteriorEalImage class Defines various parameters that define the exterior
 * paint application on the vehicle Also defines a comparison function to
 * compare two InteriorEalImage
 * 
 * @author newgen
 */
public class InteriorEalImage implements Serializable {
    private static final long serialVersionUID = -7412176199557276892L;
    public static final int UNKNOWN = 99;
    public static final int VINYL = 0;
    public static final int CLOTH = 1;
    public static final int LEATHER = 2;

    /**
     * Trim code of the InteriorEalImage
     */
    private String trimCode = "";
    /**
     * Interior code of the InteriorEalImage
     */
    private String interiorCode = "";
    /**
     * Image of the InteriorEalImage
     */
    private String image = "";
    /**
     * Swatch Image of the InteriorEalImage
     */
    private String swatchImage = "";
    /**
     * Trim Type of the InteriorEalImage
     */
    private int trimType;

    /**
     * Getter for TrimCode
     */
    public String getTrimCode() {
        return this.trimCode;
    }

    /**
     * Setter for TrimCode
     */
    public void setTrimCode(String trimCode) {
        this.trimCode = trimCode;
    }

    /**
     * Getter for Interior Code
     */
    public String getInteriorCode() {
        return this.interiorCode;
    }

    /**
     * Setter for Interior Code
     */
    public void setInteriorCode(String interiorCode) {
        this.interiorCode = interiorCode;
    }

    /**
     * Getter for Image
     */
    public String getImage() {
        return this.image;
    }

    /**
     * Setter for Image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Getter for Swatch Image
     */
    public String getSwatchImage() {
        return this.swatchImage;
    }

    /**
     * Setter for Swatch Image
     */
    public void setSwatchImage(String swatchImage) {
        this.swatchImage = swatchImage;
    }

    /**
     * Getter for Trim Type
     */
    public int getTrimType() {
        return this.trimType;
    }

    /**
     * Setter for Trim Type
     */
    public void setTrimType(int trimType) {
        this.trimType = trimType;
    }

    /**
     * Override the toString() method to return the object in desired format
     * when called
     */
    @Override
    public String toString() {
        return "trim: " + this.trimCode + ", interior: " + this.interiorCode + ", image: " + this.image + ", swatch: "
                + this.swatchImage + ", TrimType: " + this.trimType;
    }

    /**
     * Compares two InteriorEalImage objects
     * 
     * @return boolean true if all the properties match, else false
     */
    public boolean compareKeys(InteriorEalImage image) {
        boolean flag = true;
        if (!this.getInteriorCode().equals(image.getInteriorCode())) {
            flag = false;
        }

        if (!this.getTrimCode().equals(image.getTrimCode())) {
            flag = false;
        }

        if (this.getTrimType() != image.getTrimType()) {
            flag = false;
        }

        return flag;
    }

}
