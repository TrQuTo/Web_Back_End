package com.tqt.WebBasic.model;

import lombok.*;

@Getter
@Setter
@Generated
@NoArgsConstructor
@ToString
public class Order {
    private int id;
    private int total;
    private String date;
    private boolean statusOrder;
    private int idProduct;
    private int idUser;
}
