package com.liku.pdfbuilder.service;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.liku.pdfbuilder.dto.UserForm;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
@Service
public class NewPdfService {
    public byte[] generatePdf(UserForm user) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);

            Document doc = new Document(pdf, PageSize.A4);

            // VERY SIMPLE CONTENT
            doc.add(new Paragraph("TEST PDF"));
            doc.add(new Paragraph("Name: " + safe(user.getName())));
            doc.add(new Paragraph("Email: " + safe(user.getEmail())));
            doc.add(new Paragraph("Mobile: " + safe(user.getMobile())));

            doc.close(); // VERY IMPORTANT

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("PDF SIZE: " + out.size());

        return out.toByteArray();
    }

    private String safe(String val) {
        return val != null ? val : "";
    }
}
