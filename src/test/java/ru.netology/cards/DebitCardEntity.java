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

//Task  - обратиться к БД со ко всем полям (*) забрать одну строку и каждую строку по отдельности распечатать!!!
//TODO: //напоминанме


