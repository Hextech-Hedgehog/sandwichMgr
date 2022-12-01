package sandwich.repository;

import com.sun.xml.bind.v2.TODO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sandwich.exception.SandwichNotFoundException;
import sandwich.model.SandwichType;
import sandwich.model.Shop;

import java.util.List;
import java.util.Set;

public interface SandwichTypeJpaRepository extends JpaRepository<SandwichType, Integer> {

    SandwichType findBySandwichName(String SandwichName) throws SandwichNotFoundException;

    // TODO refactor exact name
    SandwichType findSandwichTypeBySandwichNameAndShopId(String sandwichName, int shopId) throws SandwichNotFoundException;
    Set<SandwichType> findSandwichTypesByShopId(int shopId);  // TODO refactor exact name + Set/List ?

    @Modifying
    @Query(value = "insert into sandwichtype(stname, st_shid) values(:name, :id)", nativeQuery = true)
    void addSandwichType(@Param("id") int shopId, @Param("name") String sandwichType);

    @Modifying
    @Query(value = "delete from sandwichtype where st_shid = :id and stname = :name ", nativeQuery = true)
    void removeSandwichType(@Param("id") int shopId, @Param("name") String sandwichType);


}
