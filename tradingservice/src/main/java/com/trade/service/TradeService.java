package com.trade.service;

import java.util.List;
import java.util.Optional;
import com.trade.entity.TradeInfo;
import com.trade.repo.TradeRepository;

public class TradeService {

  private final TradeRepository tradeRepository;

  public TradeService(TradeRepository tradeRepository) {
    super();
    this.tradeRepository = tradeRepository;
  }

  public Optional<TradeInfo> tradeDetail(String ticker) {

    return tradeRepository.findByTicker(ticker);
  }

  public List<TradeInfo> tradeList() {
    return tradeRepository.findAll();
  }

}
