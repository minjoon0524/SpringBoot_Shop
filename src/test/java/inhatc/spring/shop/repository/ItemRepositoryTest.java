package inhatc.spring.shop.repository;

import com.querydsl.core.BooleanBuilder;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

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
        for (int i = 0; i <=140; i++) {
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

    public void createItemList2(){
        for (int i = 1; i <=5; i++) {
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

        for (int i = 6; i <=10; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품"+i)
                    .price(10000+i)
                    .itemDetail("테스트 상품 상세 설명"+i)
                    .itemSellStatus(ItemSellStatus.SOLD_OUT)
                    .stockNumber(100+i)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("querydsl 테스트2")
    public void querydslTest2(){
        createItemList2();
        BooleanBuilder builder=new BooleanBuilder(); // 있을 수도 있고 없을 수도 있다.
        QItem item = QItem.item;

        String itemDetail="테스트";
        int price=10003;
        String itemSellStatus = "SELL";

        builder.and(item.itemDetail.like("%"+itemDetail+"%"));
        builder.and(item.price.gt(price));

        if(StringUtils.equals(itemSellStatus, ItemSellStatus.SELL)){
            builder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        Pageable pageable= PageRequest.of(0,5);
        Page<Item> page = itemRepository.findAll(builder, pageable);
        List<Item> content = page.getContent();
        content.stream().forEach((e)->{
            System.out.println(e);
        });

    }

    //1. 쿼리메서드
    @Test
    @DisplayName("쿼리메서드 테스트")
    public void findByNumberGreaterThanEqualAndItemNmContainingTest(){
        createItemList();
        itemRepository.findByStockNumberGreaterThanEqualAndItemNmContaining(140, "3")
                .forEach((item) -> {
                    System.out.println(item);
                });

    }

    //2.  JPQL 이용해서 위에 조건
    @Test
    @DisplayName("JPQL 과제테스트")
    public void findByNmTest(){
        createItemList();
        itemRepository.findByNm("3")
                .forEach((item)->{
                    System.out.println(item);
                });
    }

    //3.  Native 로 위에 조건
    @Test
    @DisplayName("Native 과제테스트")
    public void findByNmNative(){
        createItemList();
        itemRepository.findByNmNative("3")
                .forEach((item)->{
                    System.out.println(item);
                });
    }

    //4.  querydsl로 위에 조건
    @Test
    @DisplayName("querydsl 과제테스트")
    public void findByNmQueryDslTest(){
        createItemList();

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item;
        List<Item> items = queryFactory.select(qItem)
                .from(qItem)
                .where(qItem.stockNumber.goe(140))
                .where(qItem.itemNm.like("%"+"3"+"%"))
                .fetch();

        items.forEach((item) -> System.out.println(item));

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