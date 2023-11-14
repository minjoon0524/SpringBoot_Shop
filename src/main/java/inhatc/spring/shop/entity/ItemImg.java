package inhatc.spring.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_img_id")
    private Long id;

    private String imgName; // 이미지 파일명
    private String oriImgName; // 이미지 원본파일명
    private String imgUrl; //이미지  URL
    private String repImgYn;// 대표이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void updateItemImg(String imgName,String oriImgName,String imgUrl){
        this.imgName=imgName;
        this.oriImgName=oriImgName;
        this.imgUrl=imgUrl;
    }

}
