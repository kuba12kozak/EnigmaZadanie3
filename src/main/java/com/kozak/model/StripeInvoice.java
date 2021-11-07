package com.kozak.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StripeInvoice {
    private String id;
    private String currency;
    private Long created;
    private String description;
    private Long total;
    private String status;
    private String accountName;
}
