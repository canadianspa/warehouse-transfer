package gui;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entities.TransferJob;

public class PDFCreator {
	private static String FILE = "mypdf.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
	public static void print(TransferJob t) {
		try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addContent(document,t);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
	
		
	
	 private static void addMetaData(Document document) {
	        document.addTitle("Warehouse Transfer");
	        document.addSubject("Warehouse Transfer");
	        document.addKeywords("Warehouse, Transfer");
	        document.addAuthor("Jake Labelle");
	        document.addCreator("Jake Labelle");
	    }

	    

	    private static void addContent(Document document, TransferJob t) throws DocumentException {
	        Anchor anchor = new Anchor("Warehouse Transfer", catFont);
	        anchor.setName("Warehouse Transfer");

	        // Second parameter is the number of the chapter
	        Chapter warehouseTransfer = new Chapter(new Paragraph(anchor), 1);

	        Paragraph transferInformation = new Paragraph("Information", subFont);
	        Section subCatPart = warehouseTransfer.addSection(transferInformation);
	        subCatPart.add(new Paragraph("Hello"));

	        subPara = new Paragraph("Subcategory 2", subFont);
	        subCatPart = warehouseTransfer.addSection(subPara);
	        subCatPart.add(new Paragraph("Paragraph 1"));
	        subCatPart.add(new Paragraph("Paragraph 2"));
	        subCatPart.add(new Paragraph("Paragraph 3"));

	        // add a list
	        createList(subCatPart);
	        Paragraph paragraph = new Paragraph();
	        addEmptyLine(paragraph, 5);
	        subCatPart.add(paragraph);

	        // add a table
	        createTable(subCatPart);

	        // now add all this to the document
	        document.add(warehouseTransfer);
s

	    }

	    private static void createTable(Section subCatPart)
	            throws BadElementException {
	        PdfPTable table = new PdfPTable(3);

	        // t.setBorderColor(BaseColor.GRAY);
	        // t.setPadding(4);
	        // t.setSpacing(4);
	        // t.setBorderWidth(1);

	        PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);

	        c1 = new PdfPCell(new Phrase("Table Header 2"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);

	        c1 = new PdfPCell(new Phrase("Table Header 3"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);
	        table.setHeaderRows(1);

	        table.addCell("1.0");
	        table.addCell("1.1");
	        table.addCell("1.2");
	        table.addCell("2.1");
	        table.addCell("2.2");
	        table.addCell("2.3");

	        subCatPart.add(table);

	    }

	    private static void createList(Section subCatPart) {
	        List list = new List(true, false, 10);
	        list.add(new ListItem("First point"));
	        list.add(new ListItem("Second point"));
	        list.add(new ListItem("Third point"));
	        subCatPart.add(list);
	    }

	    private static void addEmptyLine(Paragraph paragraph, int number) {
	        for (int i = 0; i < number; i++) {
	            paragraph.add(new Paragraph(" "));
	        }
	    }
	}


