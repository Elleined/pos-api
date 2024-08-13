package com.elleined.pos_api.controller.receipt;

import com.elleined.pos_api.model.order.Order;
import com.elleined.pos_api.model.store.Store;
import com.elleined.pos_api.service.receipt.ReceiptPDFGenerator;
import com.elleined.pos_api.service.order.OrderService;
import com.elleined.pos_api.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/generate/receipt")
@RequiredArgsConstructor
public class ReceiptPDFGeneratorController {

    private final ReceiptPDFGenerator receiptPDFGenerator;

    private final StoreService storeService;
    private final OrderService orderService;

    @GetMapping
    public void generate(@RequestParam("storeId") int storeId,
                         @RequestParam("orderId") int orderId,
                         @RequestParam("cash") BigDecimal cash) throws FileNotFoundException {

        Store store = storeService.getById(storeId);
        Order order = orderService.getById(orderId);

        receiptPDFGenerator.generate(store, order, cash);
    }
}
