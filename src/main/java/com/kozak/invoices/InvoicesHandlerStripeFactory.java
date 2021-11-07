package com.kozak.invoices;

public final class InvoicesHandlerStripeFactory {
    private InvoicesHandlerStripeFactory() {
    }

    public static InvoicesHandler createInvoiceHandlerStripe() {
        return new InvoicesHandlerStripe();
    }
}
