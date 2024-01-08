package uz.brogrammers.eshop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.brogrammers.eshop.order.entity.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "Select * from `order` where user_id = :userId", nativeQuery = true)
    List<Order> findAllByUserId(@Param("userId") Integer userId);

    @Query(value = "Select * from `order` where id= :id", nativeQuery = true)
    Optional<Order> findById(@Param("id") Integer id);

    @Override
    @Query(value = "Select * from `order`", nativeQuery = true)
    List<Order> findAll();

}
