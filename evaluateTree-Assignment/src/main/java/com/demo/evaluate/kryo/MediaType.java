package com.demo.evaluate.kryo;

/**
 * Extend MediaType to include a definition for Kryo.
 *
 * @author MacKinnonM
 */
public class MediaType extends javax.ws.rs.core.MediaType {
    public static final String APPLICATION_KRYO = "application/x-kryo";

    public static final javax.ws.rs.core.MediaType APPLICATION_KRYO_TYPE =
            new javax.ws.rs.core.MediaType("application", "x-kryo");
}
