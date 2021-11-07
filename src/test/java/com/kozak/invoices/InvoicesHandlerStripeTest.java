package com.kozak.invoices;

import com.kozak.exceptions.StripeAPIException;
import com.kozak.exceptions.ValidationException;
import com.stripe.model.Invoice;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class InvoicesHandlerStripeTest {
    private final static String API_KEY = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";
    private final static String CUSTOMER_ID = "cus_4QE0v7gcdox28x";
    private final static String CURRENCY = "usd";
    private final static Integer AMOUNT = 2;

    private final static String VALIDATION_EXCEPTION_MESSAGE_API_KEY = "apiKey must not be null";

    private final InvoicesHandler invoicesHandler = new InvoicesHandlerStripe();

    @Test
    void createInvoiceSuccessfully() throws ValidationException, StripeAPIException {
        Map<String, String> map = new HashMap<>();
        assertNotNull(invoicesHandler.createInvoice(API_KEY, CUSTOMER_ID, map, CURRENCY, AMOUNT, map));
    }

    @Test
    void getInvoiceByIdSuccessfully() throws ValidationException, StripeAPIException {
        Map<String, String> map = new HashMap<>();
        Invoice invoice = invoicesHandler.createInvoice(API_KEY, CUSTOMER_ID, map, CURRENCY, AMOUNT, map);
        Invoice invoiceById = invoicesHandler.getInvoiceById(API_KEY, invoice.getId());
        assertNotNull(invoiceById);
        assertEquals(invoice.getId(), invoiceById.getId());
        assertEquals(invoice.getCurrency(), invoiceById.getCurrency());
        assertEquals(invoice.getLines(), invoiceById.getLines());
    }

    @Test
    void createInvoiceThrowsValidationError() {
        Map<String, String> map = new HashMap<>();
        ValidationException validationException = assertThrows(ValidationException.class, () -> {
            invoicesHandler.createInvoice(null, null, map, CURRENCY, AMOUNT, map);
        });
        assertEquals(validationException.getMessage(), VALIDATION_EXCEPTION_MESSAGE_API_KEY);
    }

    @Test
    void createInvoiceThrowsStripeError() {
        Map<String, String> map = new HashMap<>();
        assertThrows(StripeAPIException.class, () -> {
            invoicesHandler.createInvoice("somerandomapikeythatdoesnotexist", CUSTOMER_ID, map, CURRENCY, AMOUNT, map);
        });
    }
}