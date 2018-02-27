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
    public void convert(String inputFileName, String outputFileName) {
        try {
            Workbook doc = new Workbook(inputFileName);
            PdfSaveOptions options = new PdfSaveOptions();
            options.setOnePagePerSheet(true);
            
            if (this.pageCount > 0) {
                options.setPageCount(this.pageCount);
            }
            
            File tempOutput = File.createTempFile("snapshot_output_", ".pdf");
            FileOutputStream fwos = new FileOutputStream(tempOutput);
            doc.save(fwos, options);
            fwos.close();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Encountered error while converting. {0}", ex);
        }
    }

    @Override
    public void setPageCount(int count) {
        this.pageCount = count;
    }
    
}
