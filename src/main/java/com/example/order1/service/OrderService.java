package com.example.order1.service;


import com.example.order1.dto.OrderDTO;
import com.example.order1.exception.OrderException;
import com.example.order1.model.Order;
import com.example.order1.model.User;
import com.example.order1.repo.OrderRepo;
import com.example.order1.util.EmailSenderService;
import com.example.order1.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderInterface {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService emailSender;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Order addOrder(OrderDTO orderDTO) {
        Object userDetails = restTemplate.getForObject("http://localhost:8085/user/get/" + orderDTO.getUserid(), Object.class);
        System.out.println(userDetails.toString());
        Object bookDetails = restTemplate.getForObject("http://localhost:8086/book/get/" + orderDTO.getBookId(), Object.class);
        System.out.println(bookDetails.toString());
        if (userDetails.equals(null) && bookDetails.equals(null)) {
            throw new OrderException(" userid and bookid is invalid");
        }
        Order orderDetails = new Order(orderDTO);
        orderRepo.save(orderDetails);
        return orderDetails;
    }

    @Override
    public List<Order> getall() {
        List<Order> order = orderRepo.findAll();
        return order;
    }

    @Override
    public Order FindById(int id) {
        Optional<Order> order = orderRepo.findById(id);
        return order.get();
    }

    @Override
    public void getOrderbyId(int id) {
        Optional<Order> findById = orderRepo.findById(id);
        if (findById.isPresent()){
            orderRepo.deleteById(id);
        } else throw new OrderException("Id:"+id+" not present");
    }

    @Override
  public  String editById(int id, OrderDTO orderDTO){

        User user = restTemplate.getForObject("http://localhost:8085/user/insert" + orderDTO.getBookId(), User.class);
    Object bookDetails = restTemplate.getForObject("http://localhost:8086/book/get/" +orderDTO.getBookId(), Object.class);
        System.out.println(bookDetails.toString());
    Order editorder = orderRepo.findById(id).orElse(null);
        if (bookDetails.equals(null)&&bookDetails.equals(null)&&editorder.equals(null))
                throw new OrderException(" id is invalid");
            else{
        editorder.setPrice(orderDTO.getPrice());
        editorder.setQuantity(orderDTO.getQuantity());
        editorder.setAddress(orderDTO.getAddress());
        editorder.setUserId(orderDTO.getUserid());
        editorder.setBookId(orderDTO.getBookId());
        editorder.setCancel(orderDTO.isCancel());
        editorder.setEmail(orderDTO.getEmail());
        orderRepo.save(editorder);
        String token = tokenUtil.createToken(editorder.getOrderID());
        emailSender.sendEmail(orderDTO.getEmail(), "Added Your Details", "http://localhost:8080/user/retrieve/" + token);
        return token;


            }}
}