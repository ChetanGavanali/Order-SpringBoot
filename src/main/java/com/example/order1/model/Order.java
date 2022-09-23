package com.example.order1.model;

import com.example.order1.dto.OrderDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name="Book")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId", nullable = false)
    int orderID;
     int userId;

     int bookId;
    LocalDate date = LocalDate.now();
    int price;
    int quantity;
    String address;
    boolean cancel;

    String email;

    public Order(OrderDTO orderDTO){

    this.userId=orderDTO.getUserid();
    this.bookId=orderDTO.getBookId();
        this.date=orderDTO.getDate();
        this.price=orderDTO.getPrice();
        this.quantity=orderDTO.getQuantity();
        this.address=orderDTO.getAddress();
        this.cancel=orderDTO.isCancel();
        this.email=orderDTO.getEmail();
}
}