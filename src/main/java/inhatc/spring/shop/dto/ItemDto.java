package inhatc.spring.shop.dto;

import inhatc.spring.shop.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private Long id;
    private String itemNm;       // 상품명
    private int price;           // 상품 가격
    private int stockNumber;     // 상품 재고 수량
    private String itemDetail;   // 상품 상세 설명
    private String itemSellStatus;
    private String regTime;      // 등록시간
    private String updateTime;   // 수정시간
}

