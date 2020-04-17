package com.reg.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.reg.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

  Optional<User> findByName(String userName);

}
