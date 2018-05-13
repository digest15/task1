package com.edu.task1.reports.Print;

import com.edu.task1.reports.Reportable;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class PrintToPdf implements ReportPrintable {
    @Override
    public void print(Reportable report) {
        String reportName = report.getTitle() + "_" + new Date() + ".pdf";
        String catalogName = "Report";

        try (PdfWriter writer = new PdfWriter(Files.newOutputStream(Paths.get(catalogName, reportName)))) {
            PdfDocument pdf = new PdfDocument(writer);
            Document doc = new Document(pdf, PageSize.A4);

            Paragraph title = new Paragraph(report.getTitle());
            title.add("Ну вот заголовок");
            title.setTextAlignment(TextAlignment.CENTER);
            doc.add(title);

            Paragraph body = new Paragraph(report.getBody());
            doc.add(body);

            Paragraph basement = new Paragraph(report.getBasement());
            doc.add(basement);

            doc.close();
        } catch (IOException e) {
            System.err.println("Oшибкa ввода-вывода : " + reportName + e);
        }

    }
}
