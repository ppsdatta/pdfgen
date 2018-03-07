package com.planview.pdfgen2;

import com.aspose.cells.PdfSaveOptions;
import com.aspose.cells.Workbook;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sourav
 */
public class ExcelConverter implements Converter {

    private int pageCount = 0;

    public ExcelConverter() {
        this.pageCount = 0;
    }

    @Override
    public int getPageCount() {
        return this.pageCount;
    }

    @Override
    public void convert(String inputFileName, String outputFileName) throws Exception {
        try {
            Workbook doc;

            if (inputFileName == null) {
                doc = new Workbook(System.in);
            } else {
                doc = new Workbook(inputFileName);
            }

            PdfSaveOptions options = new PdfSaveOptions();
            options.setOnePagePerSheet(true);

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
            throw ex;
        }
    }

    @Override
    public void setPageCount(int count) {
        this.pageCount = count;
    }

}
