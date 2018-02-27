package com.planview.pdfgen2;

import com.aspose.words.License;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author sourav
 */
public class Main {
    static {
        try {
            License license = new License();
            license.setLicense(com.pp.snapshot.LicenseReader.readLicense());
        } catch (Exception ex) {
            Logger.getAnonymousLogger().warning("No license found, using trial version!");
        }
    }
    
    
    public static void main(String[] args) {
        if (args.length < 2) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Please provide input and output file paths");
            System.exit(1);
        }
        
        ConverterBuilder builder = new ConverterBuilder();
        builder.setFileName(args[0]);
        builder.get();
    }
}
