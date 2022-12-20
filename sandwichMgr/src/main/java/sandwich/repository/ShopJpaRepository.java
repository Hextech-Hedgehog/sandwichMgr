package sandwich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sandwich.model.entities.SandwichType;
import sandwich.model.entities.Shop;

import java.util.List;

@Repository
public interface ShopJpaRepository extends JpaRepository<Shop, Integer> {
    Shop findShopByShopName(String name);
    @Query(value="select stid, stname from sandwichtype st where st.stname=:sname and st_shid=:id", nativeQuery = true)
    List<Object[]> findSandwichByShopAndName(@Param("id") int shopId, @Param("sname") String sandwichName);
    @Query(value = "select * from sandwichtype st where st_shid=:id", nativeQuery = true)
    List<SandwichType> findSandwichesByShop(@Param("id") int shopId);
    @Query(value = "select * from sandwichtype st where st_shid=:id and LOWER(STNAME) ~ :keyword", nativeQuery = true)
    List<Object[]> findSandwichesByShopAndKeyword(@Param("id") int shopId, @Param("keyword") String keyWord);//@Param("keyword") String keyWord
    @Query(value="select * from shop where shid in (select st_shid from sandwichtype where stid=:id)", nativeQuery = true)
    Shop findShopOfSandwichType(@Param("id") int sandwichTypeId);
}
