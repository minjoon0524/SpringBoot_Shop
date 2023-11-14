package inhatc.spring.shop.dto;

import inhatc.spring.shop.constant.ItemSellStatus;
import inhatc.spring.shop.entity.Item;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemFormDto {
    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값 입니다.")
    private String itemNm;       // 상품명

    @NotNull(message = "가격은 필수 입력 값 입니다.")
    private int price;           // 상품 가격

    @NotBlank(message = "수량은 필수 입력 값입니다.")
    private int stockNumber;     // 상품 재고 수량

    private String itemDetail;   // 상품 상세 설명
    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList=new ArrayList<>();

    private List<Long> itemImgIds=new ArrayList<>();

    private static ModelMapper modelMapper=new ModelMapper();

    public Item createItem(){
        return modelMapper.map(this,Item.class);
    }

    public static ItemFormDto entityTodto(Item item){
        return modelMapper.map(item,ItemFormDto.class);
    }


}