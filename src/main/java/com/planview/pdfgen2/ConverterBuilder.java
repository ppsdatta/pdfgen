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
    private String fileName = null;
    private String fileType = null;

    public ConverterBuilder setPageCount(int count) {
        this.count = count;
        return this;
    }

    public ConverterBuilder setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public Converter get() {
        try {
            Converter converter = null;

            if (fileType != null) {
                if (fileType.equals("word")) {
                    converter = new WordConverter();
                } else if (fileType.equals("excel")) {
                    converter = new ExcelConverter();
                } else if (fileType.equals("ppt")) {
                    converter = new PowerPointConverter();
                }
            } else {
                if (fileName == null) {
                    return null;
                }
                
                Path path = FileSystems.getDefault().getPath(this.fileName);
                File file = path.toFile();
                Tika tika = new Tika();
                String type = tika.detect(file);

                Logger.getAnonymousLogger().log(Level.INFO, "file MIME type {0}", type);

                if (type.equals("application/vnd.ms-powerpoint")) {
                    converter = new PowerPointConverter();
                } else if (type.equals("application/vnd.ms-excel")) {
                    converter = new ExcelConverter();
                } else if (type.equals("application/msword")) {
                    converter = new WordConverter();
                } else if (type.contains("officedocument")) {
                    if (type.contains("wordprocessing")) {
                        converter = new WordConverter();
                    } else if (type.contains("sheet")) {
                        converter = new ExcelConverter();
                    } else if (type.contains("presentation")) {
                        converter = new PowerPointConverter();
                    }
                }
            }

            if ((converter != null) && (this.count > 0)) {
                converter.setPageCount(count);
            }

            return converter;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, ex.toString());
            return null;
        }
    }

    public ConverterBuilder setFileType(String fileType) throws Exception {
        if (fileType.equals("word")
                || fileType.equals("excel")
                || fileType.equals("ppt")) {
            this.fileType = fileType;
            return this;
        }

        throw new Exception("This file type is not supported");
    }

}
