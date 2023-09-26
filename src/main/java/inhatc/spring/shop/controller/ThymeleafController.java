    package inhatc.spring.shop.controller;

    import inhatc.spring.shop.dto.ItemDto;
    import inhatc.spring.shop.entity.Item;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;

    @Controller
    public class ThymeleafController {
        @GetMapping("/thymeleaf/ex1")
        public String ex1(Model model){
            ItemDto itemDto=ItemDto.builder()
                    .itemNm("상품명1")
                    .itemSellStatus("SELL")
                    .price(10000)
                    .itemDetail("상품 상세 설명")
                    .build();
            model.addAttribute("itemDto",itemDto);

            return "thymeleaf/ex1";
        }
    }
