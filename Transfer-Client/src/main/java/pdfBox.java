import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class pdfBox {

	static PDDocument doc;
	public static void main(String[] args) {
		try {
			doc = PDDocument.load(new File("stock-tranfer-form.pdf"));
			printFields();
			
			doc.close();

		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@SuppressWarnings("rawtypes")
	public static void printFields() throws IOException {
		PDDocumentCatalog docCatalog = doc.getDocumentCatalog();
		PDAcroForm acroForm = docCatalog.getAcroForm();
		List fields = acroForm.getFields();
		Iterator fieldsIter = fields.iterator();

		System.out.println(new Integer(fields.size()).toString() + " top-level fields were found on the form");

		while( fieldsIter.hasNext()) {
			PDField field = (PDField)fieldsIter.next();
			processField(field, "|--", field.getPartialName());
		}
	}

	 public static void setField(String name, String value ) throws IOException {
	        PDDocumentCatalog docCatalog = doc.getDocumentCatalog();
	        PDAcroForm acroForm = docCatalog.getAcroForm();
	        PDField field = acroForm.getField( name );
	        if( field != null ) {
	            field.setValue(value);
	        }
	        else {
	            System.err.println( "No field found with name:" + name );
	        }
	    }
	
	@SuppressWarnings("rawtypes")
	private static void processField(PDField field, String sLevel, String sParent) throws IOException
	{
		List kids = field.getWidgets();
		if(kids != null) {
			Iterator kidsIter = kids.iterator();
			if(!sParent.equals(field.getPartialName())) {
				sParent = sParent + "." + field.getPartialName();
			}

			System.out.println(sLevel + sParent);

			while(kidsIter.hasNext()) {
				Object pdfObj = kidsIter.next();
				if(pdfObj instanceof PDField) {
					PDField kid = (PDField)pdfObj;
					processField(kid, "|  " + sLevel, sParent);
				}
			}
		}
		else {
			String outputString = sLevel + sParent + "." + field.getPartialName() + ",  type=" + field.getClass().getName();
			System.out.println(outputString);
		}
	}

}
