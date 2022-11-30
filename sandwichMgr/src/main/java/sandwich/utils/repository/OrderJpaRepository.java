package sandwich.utils.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sandwich.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Integer> {

    @Modifying
    @Query(value="insert into orders (odate) values (:date)", nativeQuery = true)
    void addOrder(@Param("date") LocalDate date);
    @Query(value = "select * from orders where odate=:date", nativeQuery = true)
    List<Order> findOrderByDate(@Param("date") LocalDate date);
    @Query(value="select * from orders where orid=:id", nativeQuery = true)
    Order findOrderById(@Param("id") int orderId);
    @Modifying
    @Query(value="delete from orders where orid=:id", nativeQuery = true)
    void removeOrder(@Param("id") int orderId);
    @Query(value="select * from orders", nativeQuery = true)
    List<Order> getOrders();
}
