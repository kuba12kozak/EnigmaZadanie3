package com.kozak.invoices;

import com.kozak.exceptions.StripeAPIException;
import com.kozak.exceptions.ValidationException;
import com.kozak.model.StripeInvoice;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.net.RequestOptions;

import java.util.HashMap;
import java.util.Map;


class InvoicesHandlerStripe implements InvoicesHandler {

    @Override
    public StripeInvoice createInvoice(String apiKey,
                                       String customer,
                                       Map<String, String> invoiceAdditionalDetails,
                                       String currency,
                                       Integer amount,
                                       Map<String, String> invoiceItemAdditionalDetails) throws StripeAPIException, ValidationException {
        validateDataForCreatingInvoice(apiKey, customer, currency, amount);
        saveInvoiceItem(apiKey, customer, currency, amount);
        return saveInvoice(apiKey, customer, invoiceAdditionalDetails);
    }

    @Override
    public StripeInvoice getInvoiceById(String apiKey, String invoiceId) throws StripeAPIException, ValidationException {
        validateDataForGettingInvoiceById(apiKey, invoiceId);

        try {
            Invoice invoice = Invoice.retrieve(invoiceId, getRequestOptions(apiKey));
            return StripeInvoice.builder()
                    .id(invoice.getId())
                    .accountName(invoice.getAccountName())
                    .created(invoice.getCreated())
                    .description(invoice.getDescription())
                    .status(invoice.getStatus())
                    .total(invoice.getTotal())
                    .currency(invoice.getCurrency())
                    .build();
        } catch (StripeException e) {
            throw new StripeAPIException(e.getMessage());
        }
    }

    private void saveInvoiceItem(String apiKey, String customer, String currency, Integer amount) throws StripeAPIException {
        Map<String, Object> invoiceItemParams = new HashMap<>();
        invoiceItemParams.put("customer", customer);
        invoiceItemParams.put("currency", currency);
        invoiceItemParams.put("amount", amount);

        try {
            InvoiceItem.create(invoiceItemParams, getRequestOptions(apiKey));
        } catch (StripeException e) {
            throw new StripeAPIException(e.getMessage());
        }
    }

    private StripeInvoice saveInvoice(String apiKey, String customer, Map<String, String> invoiceAdditionalDetails) throws StripeAPIException {
        Map<String, Object> invoiceParams = new HashMap<>();
        invoiceParams.put("customer", customer);
        invoiceParams.putAll(invoiceAdditionalDetails);

        try {
            Invoice invoice = Invoice.create(invoiceParams, getRequestOptions(apiKey));
            return StripeInvoice.builder()
                    .id(invoice.getId())
                    .accountName(invoice.getAccountName())
                    .created(invoice.getCreated())
                    .description(invoice.getDescription())
                    .status(invoice.getStatus())
                    .total(invoice.getTotal())
                    .currency(invoice.getCurrency())
                    .build();
        } catch (StripeException e) {
            throw new StripeAPIException(e.getMessage());
        }
    }

    private void validateDataForCreatingInvoice(String apiKey, String customer, String currency, Integer amount) throws ValidationException {
        if (apiKey == null) {
            throw new ValidationException("apiKey must not be null");
        }
        if (customer == null) {
            throw new ValidationException("customer must not be null");
        }
        if (amount == null) {
            throw new ValidationException("amount must not be null");
        }
        if (currency == null) {
            throw new ValidationException("currency must not be null");
        }
    }

    private void validateDataForGettingInvoiceById(String apiKey, String invoiceId) throws ValidationException {
        if (apiKey == null) {
            throw new ValidationException("apiKey must not be null");
        }
        if (invoiceId == null) {
            throw new ValidationException("invoiceId must not be null");
        }
    }

    private RequestOptions getRequestOptions(String apiKey) {
        return RequestOptions.builder()
                .setApiKey(apiKey)
                .build();
    }
}
