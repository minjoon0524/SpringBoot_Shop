package inhatc.spring.shop.dto;

import inhatc.spring.shop.constant.ItemSellStatus;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemFormDto {
    private Long id;
    private String itemNm;       // 상품명
    private int price;           // 상품 가격
    private int stockNumber;     // 상품 재고 수량
    private String itemDetail;   // 상품 상세 설명
    private ItemSellStatus itemSellStatus;


}
