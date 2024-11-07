package com.yk.tg_bot_get_crypto_rate.model;

import lombok.Data;

@Data
public class CurrencyModel {

    private Integer mins;

    private Double price;

    private Long closeTime;


}
