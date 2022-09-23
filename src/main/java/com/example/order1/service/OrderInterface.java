package com.example.order1.service;


import com.example.order1.dto.OrderDTO;
import com.example.order1.model.Order;

import java.util.List;

public interface OrderInterface {

    Order addOrder(OrderDTO orderDTO);
    

    Order FindById(int id);

    void getOrderbyId(int id);

    String editById(int id, OrderDTO orderDTO);


    List<Order> getall();
}
