package z.z.service;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.util.Map;

public class PDFView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,String> userData = (Map<String,String>) model.get("userData");

        Table table = new Table(2);
        table.addCell("Roll No");
        table.addCell("Name");

        for (Map.Entry<String, String> entry : userData.entrySet()) {
            table.addCell(entry.getKey());
            table.addCell(entry.getValue());
        }
        document.add(table);
    }
}
