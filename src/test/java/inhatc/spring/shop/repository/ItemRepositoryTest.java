package inhatc.spring.shop.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inhatc.spring.shop.constant.ItemSellStatus;
import inhatc.spring.shop.entity.Item;
import inhatc.spring.shop.entity.QItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
//@Transactional
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;


    @PersistenceContext
    EntityManager em;

    public void createItemList(){
        for (int i = 0; i <=10; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품"+i)
                    .price(10000+i)
                    .itemDetail("테스트 상품 상세 설명"+i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .stockNumber(100+i)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("querydsl 테스트")
    public void querydslTest(){
        createItemList();

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item;
//        List<Tuple> list = queryFactory.select().from().where().orderBy().fetch();
        List<Item> items = queryFactory.select(qItem)
                .from(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%"+"2"+"%"))
                .orderBy(qItem.price.desc())
                .fetch();

        items.forEach((item) -> System.out.println(item));

//        for (Item item : itemList) {
//            System.out.println(item);
//        }

    }
    @Test
    @DisplayName("NativeQuery 테스트")
    public void findByItemDetailNativeTest(){
        createItemList();
        itemRepository.findByDetailNative("1")
                .forEach((item)->{
                    System.out.println(item);
                });
    }


    @Test
    @DisplayName("JPQL 테스트")
    public void findByItemDetailTest(){
        createItemList();
        itemRepository.findByDetail("1")
                .forEach((item)->{
                    System.out.println(item);
                });
    }
    @Test
    @DisplayName("정렬 테스트")
    public void findByPriceLessThanOrderByPriceDescTest(){
        createItemList();
        itemRepository.findByPriceLessThanOrderByPriceDesc(10000)
                .forEach((item) -> {
                    System.out.println(item);
                });

    }


    @Test
    @DisplayName("OR테스트")
    public void findByItemNmOrItemDetailTest(){
        createItemList();
        itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명8").forEach(System.out::println);

    }

    @Test
    @DisplayName("상품명 검색테스트")
    public void findByItemNmTest(){
        createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
        for (Item item : itemList) {
            System.out.println(item);
        }
//        itemRepository.findByItemNm("테스트상품8").forEach(System.out::println);


    }


    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest(){
        Item item = Item.builder()
                .itemNm("테스트 상품")
                .price(10000)
                .itemDetail("테스트 상품 상세 설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .stockNumber(100)
                .regTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        System.out.println("==============================================Item : "+item);
        Item savedItem = itemRepository.save(item);
        System.out.println("==============================================Item : "+savedItem);


    }



}