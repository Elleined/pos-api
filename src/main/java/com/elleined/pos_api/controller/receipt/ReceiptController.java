package com.elleined.pos_api.controller.receipt;

import com.elleined.pos_api.service.receipt.ReceiptGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receipt")
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptGenerator receiptGenerator;

    @GetMapping
    public void generate() {
        receiptGenerator.generate();
    }
}
