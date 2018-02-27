package com.planview.pdfgen2;

import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sourav
 */
public class PowerPointConverter implements Converter {
    
    private int pageCount = 0;

    @Override
    public int getPageCount() {
        return this.pageCount;
    }

    @Override
    public void setPageCount(int count) {
        this.pageCount = count;
    }

    @Override
    public void convert(String inputFileName, String outputFileName) {
        try {
            FileOutputStream fwos = new FileOutputStream(outputFileName);
            Presentation doc = new Presentation(inputFileName);    
            long numberSlides = doc.getSlides().size();
            
            if ((this.getPageCount() > 0) && (this.getPageCount() <= numberSlides)) {
                int[] slides = new int[this.getPageCount()];
                for (int i = 0; i < this.getPageCount(); i++) {
                    slides[i] = i + 1;
                }
                doc.save(fwos, slides, SaveFormat.Pdf);
            }
            else {
                doc.save(fwos, SaveFormat.Pdf);
            }
            
            fwos.close();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Encountered error while converting. {0}", ex);
        }
    }
}
