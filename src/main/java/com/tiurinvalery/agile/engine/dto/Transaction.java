package com.tiurinvalery.agile.engine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Transaction {
    @NonNull
    @JsonProperty("account_id")
    private String accountId;
    @NonNull
    @JsonProperty("sum")
    private Double sum;
    @NonNull
    @JsonProperty("action")
    private Action action;
    @NonNull
    @JsonProperty("date")
    private Date date;
    private Long transactionId;
}