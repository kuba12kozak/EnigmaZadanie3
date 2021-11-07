package com.kozak.invoices;

import com.kozak.exceptions.StripeAPIException;
import com.kozak.exceptions.ValidationException;
import com.kozak.model.StripeInvoice;
import com.stripe.model.Invoice;

import java.util.Map;

public interface InvoicesHandler {

    /**
     * Creates an invoice in Stripe system and returns created invoice item
     *
     * @param apiKey Stripe's apiKey
     * @param customer customer id in Stripe system
     * @param invoiceAdditionalDetails map containing additional invoice details
     * @param currency invoice currency
     * @param amount amount to be paid
     * @param invoiceItemAdditionalDetails map containing additional invoice items details
     * @throws StripeAPIException for any exception thrown by Stripe service
     * @throws ValidationException for any validation exception
     * @return {@link StripeInvoice} item
     */
    StripeInvoice createInvoice(String apiKey, String customer, Map<String, String> invoiceAdditionalDetails, String currency, Integer amount, Map<String, String> invoiceItemAdditionalDetails) throws StripeAPIException, ValidationException;


    /**
     * Returns an Invoice item for a given id.
     *
     * @param apiKey Stripe's apiKey
     * @param invoiceId id of the invoice to be retrieved from Stripe
     * @throws StripeAPIException for any exception thrown by Stripe service
     * @throws ValidationException for any validation exception
     * @return {@link StripeInvoice} item
     */
    StripeInvoice getInvoiceById(String apiKey, String invoiceId) throws StripeAPIException, ValidationException;
}
