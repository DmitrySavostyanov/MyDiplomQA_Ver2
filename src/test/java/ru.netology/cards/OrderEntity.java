package ru.netology.cards;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class OrderEntity {
        private String id;
        private String created;
        private String credit_id;
        private String payment_id;
}
