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
            Presentation doc;

            if (inputFileName == null) {
                doc = new Presentation(System.in);
            } else {
                doc = new Presentation(inputFileName);
            }

            long numberSlides = doc.getSlides().size();

            if ((this.getPageCount() > 0) && (this.getPageCount() <= numberSlides)) {
                int[] slides = new int[this.getPageCount()];
                for (int i = 0; i < this.getPageCount(); i++) {
                    slides[i] = i + 1;
                }

                if (outputFileName == null) {
                    doc.save(System.out, slides, SaveFormat.Pdf);
                } else {
                    try (FileOutputStream fos = new FileOutputStream(outputFileName)) {
                        doc.save(fos, slides, SaveFormat.Pdf);
                    }
                }
            } else if (outputFileName == null) {
                doc.save(System.out, SaveFormat.Pdf);
            } else {
                try (FileOutputStream fos = new FileOutputStream(outputFileName)) {
                    doc.save(fos, SaveFormat.Pdf);
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, ex.toString());
        }
    }
}
