package edu.wpi.cs3733c19.teamI.Entities;

// https://pdfbox.apache.org/
// https://pdfbox.apache.org/download.cgi
// Libraries of each subproject
// pdfbox-2.0.13.jar 2.5MB, pre-built binary ASC SHA512
// Link to be able to download files from pdf

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.pdmodel.font.*;


public class PDFManager {

    private PDFParser parser;
    private PDFTextStripper pdfStripper;
    private PDDocument pdDoc;
    private COSDocument cosDoc;

    private String Text;
    private String filePath;
    private File file;

    public PDFManager() {

    }

    public String toText() throws IOException {
        this.pdfStripper = null;
        this.pdDoc = null;
        this.cosDoc = null;

        file = new File(filePath);
        parser = new PDFParser(new RandomAccessFile(file, "r")); // update for PDFBox V 2.0

        parser.parse();
        cosDoc = parser.getDocument();
        pdfStripper = new PDFTextStripper();
        pdDoc = new PDDocument(cosDoc);
        pdDoc.getNumberOfPages();
        pdfStripper.setStartPage(0);
        pdfStripper.setEndPage(pdDoc.getNumberOfPages());
        Text = pdfStripper.getText(pdDoc);
        return Text;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public PDDocument getPdDoc() {
        return pdDoc;
    }

    //this function takes in a string that is the filepath and returns a Form
    public Form readPDF(String filePath){
        PDFManager pdfManager = new PDFManager();
        pdfManager.setFilePath(filePath);
        Form pdfForm=new Form();

        try{
            String text = pdfManager.toText();
            System.out.println(text);
        } catch (IOException ex){
            System.err.println((ex.getMessage()));
            //Logger.getLogger(FormSubmissionController.class.getName()).log(Leve1.SEVERE, null, ex);
        }
        return pdfForm;//Form object
    }

    public void editPDF() throws IOException{
        //Loading an existing document
        File file = new File("C:/Users/Gabriel Rodriguez/Downloads/testPDF.pdf");

        try {
            PDDocument document = PDDocument.load(file);

            //Retrieving the pages of the document
            PDPage page = document.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            //Begin the Content stream
            contentStream.beginText();

            //Setting the font to the Content stream
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

            //Setting the position for the line
            contentStream.newLineAtOffset(50, 10);

            String text = "TEST";

            //Adding text in the form of string
            contentStream.showText(text);

            //Ending the content stream
            contentStream.endText();

            System.out.println("Content added");

            //Closing the content stream
            contentStream.close();

            //Saving the document
            document.save(new File("C:/Users/Gabriel Rodriguez/Downloads/newForm.pdf"));

            //Closing the document
            document.close();
        }catch (IOException ex){
            System.err.println(ex);
        }
    }

}
