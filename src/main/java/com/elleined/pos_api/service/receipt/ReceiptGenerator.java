package com.elleined.pos_api.service.receipt;


import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class ReceiptGenerator {
    private final TemplateEngine templateEngine;

    public void generate() {
        Context context = new Context();
        context.setVariable("name", "Denielle");
        context.setVariable("names", List.of("name1", "name2", "name3"));

        String html = templateEngine.process("sample.html", context);
        System.out.println(html);
    }

//    public void generate() throws IOException {
//        ConverterProperties converterProperties = new ConverterProperties();
//        converterProperties.setBaseUri(RESOURCES_BASE_URI);
//
//        final String html = STR."\{RESOURCES_BASE_URI}/html/receipt.html";
//        final String pdf = STR."\{TARGET}/receipt.pdf";
//
//        HtmlConverter.convertToPdf(new FileInputStream(html), new FileOutputStream(pdf), converterProperties);
//    }
}
