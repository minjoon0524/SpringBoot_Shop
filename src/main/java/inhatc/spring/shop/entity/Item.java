package inhatc.spring.shop.entity;

import inhatc.spring.shop.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String itemNm;       // 상품명

    @Column(nullable = false)
    private int price;           // 상품 가격

    @Column(nullable = false, name="number")
    private int stockNumber;     // 상품 재고 수량

    @Lob // Large Object - CLOB, BLOB
    @Column(nullable = false)
    private String itemDetail;   // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    //    @CreatedDate
    private LocalDateTime regTime;      // 등록시간
    //    @LastModifiedDate
    private LocalDateTime updateTime;   // 수정시간
}
