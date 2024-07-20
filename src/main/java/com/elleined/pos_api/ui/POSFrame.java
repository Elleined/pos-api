package com.elleined.pos_api.ui;

import com.elleined.pos_api.mapper.product.ProductMapper;
import com.elleined.pos_api.model.product.Product;
import com.elleined.pos_api.ui.main.ProductPanel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;

@SpringBootApplication
public class POSFrame extends JFrame {
    private final ProductMapper productMapper;

    public POSFrame(ProductMapper productMapper) throws HeadlessException, IOException {
        this.productMapper = productMapper;

        ProductPanel productPanel = new ProductPanel(productMapper.toEntity("Name", "Description", "test-image.jpg", new BigDecimal(56), null));

        this.add(productPanel);

        setTitle("POS Frame");
        setVisible(true);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
