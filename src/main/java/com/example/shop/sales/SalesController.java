package com.example.shop.sales;

import com.example.shop.member.CustomUser;
import com.example.shop.member.Member;
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
        var member = new Member();
        member.setId(user.id);
        sales.setMember(member);
        salesRepository.save(sales);
        return "";
    }

    @GetMapping("/order/all")
    @ResponseBody
    String getOrderAll(){
        List<Sales> orders = salesRepository.customFindAll();
        System.out.println(orders);
        SalesDTO salesDTO = new SalesDTO();
        salesDTO.itemName = orders.get(0).getItemName();
        salesDTO.price = orders.get(0).getPrice();
        salesDTO.username = orders.get(0).getMember().getUsername();
        return "";
    }

    // @ManyToOne 단점
    // select 쿼리문 많이 실행될 수 있음
    // - JOIN 문법으로 해결가능
    // 모든컬럼 다 가져와서 귀찮음
}

class SalesDTO {
    public String itemName;
    public Integer price;
    public String username;
}
