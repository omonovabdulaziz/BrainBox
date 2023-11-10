package it.live.brainbox.repository;


import it.live.brainbox.entity.User;
import it.live.brainbox.entity.enums.SystemRoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndUniqueId(String email, String uniqueId);
    List<User> findAllBySystemRoleName(SystemRoleName systemRoleName);

}
