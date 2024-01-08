package uz.brogrammers.eshop.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.brogrammers.eshop.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
