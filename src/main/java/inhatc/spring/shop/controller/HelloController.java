package inhatc.spring.shop.controller;

import inhatc.spring.shop.dto.PersonDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
    @GetMapping("/")
    public PersonDto hello(){
        PersonDto p=new PersonDto();
        p.setAge(10);
        p.setName("홍길동");
        System.out.println("p : "+p);
      return p;


    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }
}