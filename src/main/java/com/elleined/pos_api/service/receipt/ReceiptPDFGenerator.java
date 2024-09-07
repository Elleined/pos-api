package com.elleined.pos_api.service.receipt;

import com.elleined.pos_api.model.order.Order;
import com.elleined.pos_api.model.store.Store;
import com.elleined.pos_api.service.utils.Formatter;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ReceiptPDFGenerator {
    private final TemplateEngine templateEngine;

    public void generate(Store store, Order order, BigDecimal cash) throws FileNotFoundException {
        Context context = new Context();
        context.setVariable("store", store);
        context.setVariable("order", order);
        context.setVariable("orderedProducts", order.getOrderedProducts());
        context.setVariable("createdAt", Formatter.formatDate(order.getCreatedAt()));

        // amount = price * quantity of order

        context.setVariable("totalAmount", order.getTotal()); // sum of all ordered products
        context.setVariable("cash", cash);
        context.setVariable("change", cash.subtract(order.getTotal()));  // cash - totalAmount

        String html = templateEngine.process("receipt.html", context);

        HtmlConverter.convertToPdf(html,
                new FileOutputStream("src/main/resources/receipt/receipt.pdf"));

        log.debug("Generating PDF success.");
    }
}
