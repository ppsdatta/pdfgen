package com.planview.pdfgen2;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tika.Tika;

/**
 *
 * @author sourav
 */
public class ConverterBuilder {
    private int count = 0;
    private String fileName = "";
    
    public ConverterBuilder setPageCount(int count) {
        this.count = count;
        return this;
    }
    
    public ConverterBuilder setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
    
    public Converter get() {
        if (fileName.equals("")) {
            return null;
        }
        try {
            Path path = FileSystems.getDefault().getPath(this.fileName);
            File file = path.toFile();
            Tika tika = new Tika();
            String type = tika.detect(file);
            System.out.println("Path: " + path);
            System.out.println("Type: " + type);
            return null;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Encountered error while getting a converter {0}", ex);
            return null;
        }
    }
    
}
