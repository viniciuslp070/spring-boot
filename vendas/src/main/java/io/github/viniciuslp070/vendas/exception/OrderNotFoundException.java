package io.github.viniciuslp070.vendas.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException() {
        super("Order not found.");
    }
}
