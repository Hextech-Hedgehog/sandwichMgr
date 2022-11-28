package sandwich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sandwich.model.Bill;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BillJpaRepository extends JpaRepository <Bill, Integer> {

    @Query(value="insert into bill (bdate) values (:date)", nativeQuery = true)
    @Modifying
    void addBill(@Param("date") LocalDate date);
    @Query(value="select * from bill where extract(MONTH from bdate) = date.getMonth()", nativeQuery = true)
    Bill findBillByMonth(LocalDate date);
    @Query(value="delete from bill where bid=:id", nativeQuery = true)
    @Modifying
    void removeBill(@Param("id") int billId);
    @Query(value="select * from bill", nativeQuery = true)
    List<Bill> getBills();

}
