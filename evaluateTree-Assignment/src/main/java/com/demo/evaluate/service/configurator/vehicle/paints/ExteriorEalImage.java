package com.demo.evaluate.service.configurator.vehicle.paints;

import java.io.Serializable;

/**
 * ExteriorEalImage class Defines various parameters that define the exterior
 * paint application on the vehicle Also defines a comparison function to
 * compare two ExteriorEalImages
 * 
 * @author newgen
 */
public class ExteriorEalImage implements Serializable {
    private static final long serialVersionUID = 6253154765629242808L;
    /**
     * Primary code of the ExteriorEalImage
     */
    private String primaryCode = "";
    /**
     * Secondary code of the ExteriorEalImage
     */
    private String secondaryCode = "";
    /**
     * Extra code of the ExteriorEalImage
     */
    private String extraCode = "";
    /**
     * Stripe code of the ExteriorEalImage
     */
    private String stripeCode = "";
    /**
     * Image code of the ExteriorEalImage
     */
    private String image = "";

    /**
     * Getter for Stripe code
     */
    public String getStripeCode() {
        return this.stripeCode;
    }

    /**
     * Setter for Stripe code
     */
    public void setStripeCode(String stripeCode) {
        this.stripeCode = stripeCode;
    }

    /**
     * Getter for Primary code
     */
    public String getPrimaryCode() {
        return this.primaryCode;
    }

    /**
     * Setter for Primary code
     */
    public void setPrimaryCode(String primaryCode) {
        this.primaryCode = primaryCode;
    }

    /**
     * Getter for Secondary code
     */
    public String getSecondaryCode() {
        return this.secondaryCode;
    }

    /**
     * Setter for Secondary code
     */
    public void setSecondaryCode(String secondaryCode) {
        this.secondaryCode = secondaryCode;
    }

    /**
     * Getter for Extra code
     */
    public String getExtraCode() {
        return this.extraCode;
    }

    /**
     * Setter for Extra code
     */
    public void setExtraCode(String extraCode) {
        this.extraCode = extraCode;
    }

    /**
     * Getter for Image
     */
    public String getImage() {
        return this.image;
    }

    /**
     * Set Image
     * @param image string
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Override the toString() method to return the object in desired format
     * when called
     */
    @Override
    public String toString() {
        return "primary: " + this.primaryCode + ", Secondary: " + this.secondaryCode + ", Extra: " + this.extraCode
                + ", Stripe: " + this.stripeCode + ", Image: " + this.image;
    }

    /**
     * Compares two ExteriorEalImage objects
     * 
     * @return boolean true if all the properties match, else false
     */
    public boolean compareKeys(ExteriorEalImage image) {
        boolean flag = true;

        if (!this.getPrimaryCode().equals(image.getPrimaryCode())) {
            flag = false;
        }

        if (!this.getSecondaryCode().equals(image.getSecondaryCode())) {
            flag = false;
        }

        if (!this.getExtraCode().equals(image.getExtraCode())) {
            flag = false;
        }

        return flag;
    }

}
