package com.demo.evaluate.service.configurator.vehicle.paints;

import java.io.Serializable;

/**
 * SwatchImage class Defines various parameters that define the swatch image of
 * the vehicle
 * 
 * @author newgen
 */
public class SwatchImage implements Serializable {
    private static final long serialVersionUID = 8720555485789715737L;
    private String paintCode;
    private String image;
    private String secCode;
    private String stripeCode;

    /**
     * @return the stripeCode
     */
    public String getStripeCode() {
        return this.stripeCode;
    }

    /**
     * @param stripeCode
     *            the stripeCode to set
     */
    public void setStripeCode(String stripeCode) {
        this.stripeCode = stripeCode;
    }

    /**
     * @return the paintCode
     */
    public String getPaintCode() {
        return this.paintCode;
    }

    /**
     * @param paintCode
     *            the paintCode to set
     */
    public void setPaintCode(String paintCode) {
        this.paintCode = paintCode;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return this.image;
    }

    /**
     * @param image
     *            the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the secCode
     */
    public String getSecCode() {
        return this.secCode;
    }

    /**
     * @param secCode
     *            the secCode to set
     */
    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    /**
     * Override the toString() method to return the object in desired format
     * when called
     */
    @Override
    public String toString() {
        return "code: " + this.paintCode + "secCode: " + this.secCode + ", image: " + this.image;
    }

}
