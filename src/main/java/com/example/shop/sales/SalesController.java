package com.example.shop.sales;

import com.example.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SalesController {

    private final SalesRepository salesRepository;

    @PostMapping("/order")
    @ResponseBody
    String itemOrder(String itemName, Integer price, Integer count, Authentication auth){
        Sales sales = new Sales();
        sales.setItemName(itemName);
        sales.setPrice(price);
        sales.setCount(count);
        CustomUser user = (CustomUser) auth.getPrincipal();
        salesRepository.save(sales);
        return "";
    }

    @GetMapping("/order/all")
    @ResponseBody
    String getOrderAll(){
        List<Sales> orders = salesRepository.findAll();
        System.out.println(orders.get(0));
        return "";
    }
}
