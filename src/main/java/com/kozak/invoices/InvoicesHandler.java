package com.kozak.invoices;

import com.kozak.exceptions.StripeAPIException;
import com.kozak.exceptions.ValidationException;
import com.stripe.model.Invoice;

import java.util.Map;

public interface InvoicesHandler {

    Invoice createInvoice(String apiKey, String customer, Map<String, String> invoiceAdditionalDetails, String currency, Integer amount, Map<String, String> invoiceItemAdditionalDetails) throws StripeAPIException, ValidationException;

    Invoice getInvoiceById(String apiKey, String invoiceId) throws StripeAPIException, ValidationException;
}
