package sandwich.utils.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sandwich.model.Bill;
import sandwich.model.Order;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BillJpaRepository extends JpaRepository <Bill, Integer> {

    @Query(value="insert into bill (bdate) values (:date)", nativeQuery = true)
    @Modifying
    void addBill(@Param("date") LocalDate date);
    @Query(value="select * from bill where extract(MONTH from bdate)=:date.getMonth() and extract(Year from bdate)=:date.getYear()", nativeQuery = true)
    Bill findBillByMonth(LocalDate date);
    @Query(value="delete from bill where bid=:id", nativeQuery = true)
    @Modifying
    void removeBill(@Param("id") int billId);
    @Query(value="select * from bill", nativeQuery = true)
    List<Bill> getBills();
    @Query(value="select * from orders, bill where where extract(MONTH from bdate)=:date.getMonth() and extract(Year from bdate)=:date.getYear() and orders.o_bid = bill.bid and orders.odate=:date", nativeQuery = true)
    List<Order> findOrdersByBillDate(@Param("date") LocalDate date);

}
