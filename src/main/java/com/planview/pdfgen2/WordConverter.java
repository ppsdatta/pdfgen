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
            Document doc = new Document(inputFileName);
            PdfSaveOptions options = new PdfSaveOptions();

            if (this.pageCount > 0) {
                options.setPageCount(this.pageCount);
            }

            File tempOutput = new File(outputFileName);
            FileOutputStream fwos = new FileOutputStream(tempOutput);
            doc.save(fwos, options);
            fwos.close();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, ex.toString());
        }
    }

    @Override
    public void setPageCount(int count) {
        this.pageCount = count;
    }

}
