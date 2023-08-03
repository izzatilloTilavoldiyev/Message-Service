package uz.pdp.messageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.messageservice.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserByUsername(String username);

}
