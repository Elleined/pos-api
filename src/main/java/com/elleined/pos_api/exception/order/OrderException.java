package com.elleined.pos_api.exception.order;

import com.elleined.pos_api.exception.POSException;

public class OrderException extends POSException {
    public OrderException(String message) {
        super(message);
    }
}
