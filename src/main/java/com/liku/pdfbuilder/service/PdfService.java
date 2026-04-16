package com.liku.pdfbuilder.service;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.borders.SolidBorder;
import com.liku.pdfbuilder.dto.UserForm;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.*;
import com.itextpdf.io.image.ImageDataFactory;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Base64;



@Service
public class PdfService {

    public byte[] generatePdf(UserForm user) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document doc = new Document(pdf, PageSize.A4);
            doc.setMargins(20, 20, 20, 20);

// 🎨 FULL PAGE BACKGROUND (LIGHT COLOR)
            Table bgTable = new Table(1);
            bgTable.setWidth(UnitValue.createPercentValue(100));

            Cell bgCell = new Cell()
                    .setBackgroundColor(new DeviceRgb(245, 248, 255)) // light blue background
                    .setBorder(null)
                    .setPadding(15);

            bgTable.addCell(bgCell);

            doc.add(bgTable);

// 🔷 HEADER
            Paragraph header = new Paragraph("GYANALIVE APPLICATION FORM")
                    .setFontSize(20)
                    .setBold()
                    .setFontColor(ColorConstants.WHITE)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBackgroundColor(new DeviceRgb(0, 102, 255))
                    .setPadding(12);

            doc.add(header);
            doc.add(new Paragraph("\n"));

// 🧾 MAIN CARD
            Table card = new Table(new float[]{3, 1});
            card.setWidth(UnitValue.createPercentValue(100));
            card.setMinHeight(250);

            Cell cardWrapper = new Cell().setBorder(new SolidBorder(new DeviceRgb(220,220,220),1))
                    .setPadding(10);

            Table left = new Table(2);
            left.setWidth(UnitValue.createPercentValue(100));

            left.addCell(label("Name"));
            left.addCell(value(user.getName()));

            left.addCell(label("Email"));
            left.addCell(value(user.getEmail()));

            left.addCell(label("Mobile"));
            left.addCell(value(user.getMobile()));

            left.addCell(label("Gender"));
            left.addCell(value(user.getGender()));

            left.addCell(label("DOB"));
            left.addCell(value(user.getDob()));

            card.addCell(new Cell().add(left).setBorder(null));

// 📷 PHOTO
            Cell photoCell = new Cell()
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBorder(null);

            if (user.getPhoto() != null && !user.getPhoto().isEmpty()) {
                byte[] photoBytes = Base64.getDecoder().decode(user.getPhoto());
                Image photo = new Image(ImageDataFactory.create(photoBytes))
                        .scaleToFit(120, 140);
                photoCell.add(photo);
            }

            card.addCell(photoCell);

            doc.add(card);

            doc.add(new Paragraph("\n"));

// 🏠 ADDRESS SECTION
            Paragraph addrTitle = new Paragraph("Address Details")
                    .setFontColor(ColorConstants.WHITE)
                    .setBackgroundColor(new DeviceRgb(0, 153, 102))
                    .setPadding(8)
                    .setBold();

            doc.add(addrTitle);

            Table addr = new Table(new float[]{2, 4});
            addr.setWidth(UnitValue.createPercentValue(100));
            addr.setMinHeight(120);

            addr.addCell(label("State"));
            addr.addCell(value(user.getState()));

            addr.addCell(label("District"));
            addr.addCell(value(user.getDistrict()));

            addr.addCell(label("Address"));
            addr.addCell(value(user.getAddress()));

            doc.add(addr);

            doc.add(new Paragraph("\n"));

// ✍ SIGNATURE
            Cell signCell = new Cell()
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setBorder(null);

            if (user.getSignature() != null && !user.getSignature().isEmpty()) {
                byte[] signBytes = Base64.getDecoder().decode(user.getSignature());
                Image sign = new Image(ImageDataFactory.create(signBytes))
                        .scaleToFit(120, 50);
                doc.add(sign);
            }

            doc.add(new Paragraph("Signature").setTextAlignment(TextAlignment.RIGHT));

            doc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
   System.out.println("PDF SIZE: " + out.size());
        return out.toByteArray();
    }


//public byte[] generatePdf(UserForm user) {
//
//    ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//    try {
//        PdfWriter writer = new PdfWriter(out);
//        PdfDocument pdf = new PdfDocument(writer);
//        Document doc = new Document(pdf);
//
//        // ✅ SIMPLE TEST PDF
//        doc.add(new Paragraph("HELLO PDF WORKING"));
//
//        doc.close(); // VERY IMPORTANT
//
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//
//    System.out.println("PDF SIZE: " + out.size());
//
//    return out.toByteArray();
//}

    private Cell label(String text) {
        return new Cell()
                .add(new Paragraph(text).setBold())
                .setBackgroundColor(new DeviceRgb(230, 240, 255)) // light blue
                .setPadding(6);
    }

    private Cell value(String text) {
        return new Cell()
                .add(new Paragraph(text != null ? text : ""))
                .setPadding(6);
    }
}