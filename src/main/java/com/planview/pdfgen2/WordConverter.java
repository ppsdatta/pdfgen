package com.planview.pdfgen2;

import com.aspose.words.Document;
import com.aspose.words.PdfSaveOptions;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sourav
 */
public class WordConverter implements Converter {

    private int pageCount = 0;

    public WordConverter() {
        this.pageCount = 0;
    }

    @Override
    public int getPageCount() {
        return pageCount;
    }

    @Override
    public void convert(String inputFileName, String outputFileName) {
        try {
            Document doc;
            if (inputFileName == null) {
                doc = new Document(System.in);
            } else {
                doc = new Document(inputFileName);
            }

            PdfSaveOptions options = new PdfSaveOptions();

            if (this.pageCount > 0) {
                options.setPageCount(this.pageCount);
            }

            if (outputFileName == null) {
                doc.save(System.out, options);
            } else {
                File tempOutput = new File(outputFileName);
                try (FileOutputStream fwos = new FileOutputStream(tempOutput)) {
                    doc.save(fwos, options);
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, ex.toString());
        }
    }

    @Override
    public void setPageCount(int count) {
        this.pageCount = count;
    }

}
