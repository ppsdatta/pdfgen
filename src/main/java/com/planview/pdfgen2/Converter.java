package com.planview.pdfgen2;

/**
 *
 * @author sourav
 */
public interface Converter {
    public int getPageCount();
    public void setPageCount(int count);
    public void convert(String inputFileName, String outputFileName);
}
