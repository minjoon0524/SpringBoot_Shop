package inhatc.spring.shop.repository;

import inhatc.spring.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>,QuerydslPredicateExecutor<Item> {
    List<Item> findByItemNm(String itemNm); //쿼리 메소드 기능

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);//쿼리 메소드 기능

    List<Item> findByPriceLessThanOrderByPriceDesc(int price);//쿼리 메소드 기능

    //JPQL -> Entity
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByDetail(@Param("itemDetail") String itemDetail);

    //Native Query -> DB
    @Query(value="select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByDetailNative(@Param("itemDetail") String itemDetail);


    //과제
    //1.  쿼리 메소드 (재고량과 이름으로 검색)
    List<Item> findByStockNumberGreaterThanEqualAndItemNmContaining(int stockNumber, String itemNm);

    //2.  JPQL 이용해서 위에 조건
    @Query("select i from Item i where i.stockNumber>=140 and i.itemNm like %:itemNm%")
    List<Item> findByNm(@Param("itemNm") String itemNm);

    //3.  Native 로 위에 조건
    @Query(value="select * from Item i where i.number >= 140 and i.item_nm like %:itemNm%", nativeQuery = true)
    List<Item> findByNmNative(@Param("itemNm") String itemNm);


}
