package com.planview.pdfgen2;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 *
 * @author sourav
 */


public class LicenseReader {
    public static String LICENSE_PATH_VAR = "ASPOSE_LICENSE_PATH";
    
    public static InputStream readLicense() throws Exception {
        Map<String, String> env = System.getenv();
        if (!env.containsKey(LICENSE_PATH_VAR)) {
            throw new Exception("No license found, specify license file path in ASPOSE_LICENSE_PATH environment variable.\n" +
                                "Currently using the evaluation version.");
        }
        File license = new File(env.get(LICENSE_PATH_VAR));
        return new FileInputStream(license);
    }
}

