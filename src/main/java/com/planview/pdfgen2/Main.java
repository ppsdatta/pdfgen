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

    public static void help() {
        String message = "Usage of pdfgen:\n"
                + "1. pdfgen [word|excel|ppt]  --> expects input from stdin and writes to stdout.\n"
                + "\texample: $ pdfgen ppt < inputfile > outputfile.pdf\n"
                + "2. pdfgen input_file_path output_file_path --> file type is autodetected from input file name.\n"
                + "\texample: $ pdfgen /path/to/input/file.xlsx /path/to/output/file.pdf";
        Logger.getAnonymousLogger().log(Level.INFO, message);
    }

    public static void main(String[] args) {
        String inFile = null, outFile = null;
        String fileType = null;

        switch (args.length) {
            case 1:
                Logger.getAnonymousLogger().log(Level.WARNING, "No input or output file parameters are provided, reading from stdin and writing to stdout");
                fileType = args[0];
                break;
            case 2:
                inFile = args[0];
                outFile = args[1];
                break;
            default:
                Logger.getAnonymousLogger().log(Level.SEVERE, "Invalid parameters");
                help();
                System.exit(1);
        }

        System.setProperty("java.awt.headless", "true");
        Logger.getAnonymousLogger().log(Level.INFO, "Java AWT headless environment =  {0}", java.awt.GraphicsEnvironment.isHeadless());

        ConverterBuilder builder = new ConverterBuilder();
        if (fileType == null) {
            builder.setFileName(args[0]);
        } else {
            try {
                builder.setFileType(fileType);
            } catch (Exception ex) {
                Logger.getAnonymousLogger().log(Level.SEVERE, ex.toString());
                System.exit(1);
            }
        }

        Converter converter = builder.get();

        if (converter == null) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Could not get a matching converter for this file");
            System.exit(1);
        } else {
            converter.convert(inFile, outFile);
        }
    }
}
