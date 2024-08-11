package com.elleined.pos_api.service.receipt;


import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class ReceiptGenerator {
    private final static String RESOURCES_BASE_URI = "src/main/resources/pdf";
    private final static String TARGET = "src/main/resources";

    public void generate() throws IOException {
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri(RESOURCES_BASE_URI);

        final String html = STR."\{RESOURCES_BASE_URI}/html/receipt.html";
        final String pdf = STR."\{TARGET}/receipt.pdf";

        HtmlConverter.convertToPdf(new FileInputStream(html), new FileOutputStream(pdf), converterProperties);
    }
}
