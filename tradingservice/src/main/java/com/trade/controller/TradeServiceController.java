package com.trade.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.trade.entity.TradeInfo;
import com.trade.service.TradeService;

@RestController
@RequestMapping("/trade")
public class TradeServiceController {

  private final TradeService tradeService;

  public TradeServiceController(TradeService tradeService) {
    super();
    this.tradeService = tradeService;
  }

  @GetMapping("/getTradeDetails/{ticker}")
  public ResponseEntity<TradeInfo> getTradeDetails(@PathVariable("ticker") String tickerName) {
    Optional<TradeInfo> trade = tradeService.tradeDetail(tickerName);
    if (trade.isPresent()) {
      return new ResponseEntity<>(trade.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/getAllTradeDetails")
  public ResponseEntity<List<TradeInfo>> getallTradeDetails() {
    return new ResponseEntity<>(tradeService.tradeList(), HttpStatus.OK);
  }

}
