package com.liku.pdfbuilder.controller;

import com.liku.pdfbuilder.dto.UserForm;
import com.liku.pdfbuilder.service.NewPdfService;
import com.liku.pdfbuilder.service.PdfService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {
    @Autowired
    private PdfService pdfservice;

//   @Autowired
//   private NewPdfService newPdfService;

    @PostMapping("/generatepdf")
    public ResponseEntity<byte[]>generatePdf(@RequestBody UserForm userinformation){
        byte[] pdf = pdfservice.generatePdf(userinformation);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=application.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
