package uz.brogrammers.eshop.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.brogrammers.eshop.shipping.entity.Shipping;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Integer> {
}
