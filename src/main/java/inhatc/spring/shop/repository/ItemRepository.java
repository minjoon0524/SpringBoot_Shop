package inhatc.spring.shop.repository;

import inhatc.spring.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByItemNm(String itemNm); //쿼리 메소드 기능

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);//쿼리 메소드 기능

    List<Item> findByPriceLessThanOrderByPriceDesc(int price);//쿼리 메소드 기능

    //JPQL -> Entity
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByDetail(@Param("itemDetail") String itemDetail);

    //Native Query -> DB
    @Query(value="select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByDetailNative(@Param("itemDetail") String itemDetail);
}
