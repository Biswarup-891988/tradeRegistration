package com.reg.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.web.reactive.function.client.WebClient;
import com.reg.entity.User;
import com.reg.entity.UserPortfolio;
import com.reg.exception.PasswordException;
import com.reg.exception.UserNotFoundException;
import com.reg.model.TradeInfo;
import com.reg.repo.UserPortfolioRepository;
import com.reg.repo.UserRepository;

public class RegistrationService {

  private final UserRepository userRepository;
  private final UserPortfolioRepository userPortfolioRepository;
  private final WebClient.Builder webClient;

  public RegistrationService(UserRepository userRepository, WebClient.Builder webClient,
      UserPortfolioRepository userPortfolioRepository) {
    this.userRepository = userRepository;
    this.webClient = webClient;
    this.userPortfolioRepository = userPortfolioRepository;
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public User validateUser(String userName, String password) throws Exception {

    Optional<User> user = userRepository.findByName(userName);
    if (user.isEmpty()) {
      throw new UserNotFoundException("Invalid User");
    } else {
      User userDto = user.get();
      if (!password.equals(userDto.getPassword())) {
        throw new PasswordException("Invalid Password");
      }
      return userDto;
    }

  }

  public List<User> getUserList() {
    return userRepository.findAll();
  }

  public User getUserInfo(String userName) throws Exception {
    Optional<User> user = userRepository.findByName(userName);
    if (user.isEmpty()) {
      throw new UserNotFoundException("Invalid User");
    } else {
      return user.get();
    }
  }

  public Set<UserPortfolio> getUserPortfolio(String name) throws Exception {
    Optional<User> user = userRepository.findByName(name);
    if (user.isEmpty()) {
      throw new UserNotFoundException("Invalid User");
    } else {
      return userPortfolioRepository.findByUserId(user.get().getUserId());
    }
  }

  public UserPortfolio doTrade(String name, String ticker, int units) throws UserNotFoundException {
    TradeInfo trade = webClient.build().get()
        .uri("http://localhost:9092/tradingService/trade/getTradeDetails/" + ticker).retrieve()
        .bodyToMono(TradeInfo.class).block();
    long investAmount = trade.getUnitPrice() * units;
    Optional<User> user = userRepository.findByName(name);
    if (user.isEmpty()) {
      throw new UserNotFoundException("Invalid User");
    } else {
      User userInfo = user.get();
      long remainingAmount = 0;
      if (userInfo.getBalance() > 0) {
        remainingAmount = userInfo.getBalance() - investAmount;
      }
      userInfo.setBalance(remainingAmount);
      User userDto = userRepository.save(userInfo);
      UserPortfolio portDto = savePortfolioDetails(ticker, units, investAmount, userDto);
      return portDto;
    }
  }

  private UserPortfolio savePortfolioDetails(String ticker, int units, long investAmount,
      User userInfo) {
    UserPortfolio portfolio = new UserPortfolio();
    portfolio.setAmount(investAmount);
    portfolio.setTicker(ticker);
    portfolio.setUnits(units);
    portfolio.setUserId(userInfo.getUserId());
    return userPortfolioRepository.save(portfolio);
  }

}
