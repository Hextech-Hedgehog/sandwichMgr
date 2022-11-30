package sandwich.utils.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sandwich.model.SandwichType;
import sandwich.model.Shop;

import java.util.List;

@Repository
public interface ShopJpaRepository extends JpaRepository<Shop, Integer> {
    Shop findShopByShopName(String name);
    @Query(value="select * from sandwichtype st where st.stname=:sname and st_shid=:id", nativeQuery = true)
    SandwichType findSandwichByShopAndName(@Param("id") int shopId, @Param("sname") String sandwichName);
    @Query(value = "select * from sandwichtype st where st_shid=:id", nativeQuery = true)
    List<SandwichType> findSandwichesByShop(@Param("id") int shopId);
}
