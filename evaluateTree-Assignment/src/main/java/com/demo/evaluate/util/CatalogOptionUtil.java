package com.demo.evaluate.util;

/**
 * Utility class used for getting information on configured data mode from static methods
 *
 * @author rahul.rathor
 *
 */
public class CatalogOptionUtil {

  /**
 * Return true if the data mode is 'topmaster'
 * otherwise false
 * @return {@link Boolean}
 */
    public static boolean isTopMaster(String dataMode) {
        if("Topmaster".equalsIgnoreCase(dataMode)){
            return true;
        }
        return false;
    }
}
