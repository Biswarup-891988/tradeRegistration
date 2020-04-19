package com.reg.repo;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.reg.entity.UserPortfolio;


@Repository
public interface UserPortfolioRepository extends JpaRepository<UserPortfolio,Long> {

  Set<UserPortfolio> findByUserId(long userId);


}
