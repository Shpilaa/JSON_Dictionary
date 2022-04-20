package service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class PdfService {
    // merge two lists to create a final one w/o duplicates
    public static void addLanguages(List<String> originalList, List<String> langList) {
        for (String entry : langList) {
            if (!originalList.contains(entry)) {
                originalList.add(entry);
            }
        }
    }

    // create new PDF file with statistics of last app run
    public static void createPDF(int wordNum, List<String> langList) throws FileNotFoundException, DocumentException {
        String result = "Word count: " + wordNum + ". Languages found: " + ParseService.listParser(langList);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/PDF_db/results.pdf"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.BLACK);
        Chunk chunk = new Chunk(result, font);
        document.add(chunk);
        document.close();
    }
}
