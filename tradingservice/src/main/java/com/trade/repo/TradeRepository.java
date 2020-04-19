package com.trade.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.trade.entity.TradeInfo;

public interface TradeRepository extends JpaRepository<TradeInfo, Long>{

  Optional<TradeInfo> findByTicker(String ticker);

}
