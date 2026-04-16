package com.liku.pdfbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PdfBuilderApplication {

    public static void main(String[] args) {

        SpringApplication.run(PdfBuilderApplication.class, args);
        System.out.println("pdf Builder Run... Sucessfully");
    }

}
