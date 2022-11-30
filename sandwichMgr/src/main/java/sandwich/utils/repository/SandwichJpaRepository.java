package sandwich.utils.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sandwich.model.Sandwich;

import java.util.List;

@Repository
public interface SandwichJpaRepository extends JpaRepository<Sandwich, Integer> {

    @Modifying
    @Query(value="insert into sandwich (club, butter, optional, s_uid, s_orid) values (:club, :butter, :rq, :u_id, :st_id)", nativeQuery = true)
    void addSandwich(@Param("club") String isClub, @Param("butter") String hasButter, @Param("rq") String optionalRequirement,
                     @Param("u_id") int userId, @Param("st_id") int sandwichTypeId);
    @Query(value="select * from sandwich where sid=:id", nativeQuery = true)
    Sandwich findSandwichById(@Param("id") int sandwichId);
    @Query(value="select * from sandwich where s_uid=:id", nativeQuery = true)
    List<Sandwich> findSandwichesByUser(@Param("id") int userId);
    @Modifying
    @Query(value="delete from sandwich where sid=:id", nativeQuery = true)
    void removeSandwich(@Param("id") int sandwichId);
    @Query(value="select * from sandwich", nativeQuery = true)
    List<Sandwich> findSandwiches();
}
