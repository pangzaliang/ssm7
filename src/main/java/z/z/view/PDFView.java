package z.z.view;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.util.Map;

@Component
public class PDFView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) {

        Table table = new Table(2);
        table.addCell("Roll No");
        table.addCell("Name");

        for (Map.Entry<String, Object> entry : model.entrySet()) {
            table.addCell(entry.getKey());
            table.addCell(entry.getValue().toString());
        }
        document.add(table);
    }
}
