package ru.netology.cards;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DebitCardEntity {

    private String id;
    private String amount;
    private String created;
    private String status;
    private String transaction_id;
}



