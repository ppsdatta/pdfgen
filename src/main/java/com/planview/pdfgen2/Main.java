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
            license.setLicense(LicenseReader.readLicense());
        } catch (Exception ex) {
            Logger.getAnonymousLogger().warning("No license found, using trial version!");
        }
    }
    
    
    public static void main(String[] args) {
        if (args.length < 2) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Please provide input and output file paths");
            System.exit(1);
        }
        
        System.setProperty("java.awt.headless", "true");
        Logger.getAnonymousLogger().log(Level.INFO, "Java AWT headless environment =  " + java.awt.GraphicsEnvironment.isHeadless());
        
        ConverterBuilder builder = new ConverterBuilder();
        builder.setFileName(args[0]);
        Converter converter = builder.get();
        
        if (converter == null) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Could not get a matching converter for this file");
            System.exit(1);
        }
        else {
            converter.convert(args[0], args[1]);
        }
    }
}
