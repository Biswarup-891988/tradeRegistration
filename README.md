# tradeRegistration

Its a application consists of two eureka client and one eureka server . 

registration-service : It's a Eureka Client registered in the eureka server. User can register in the trade application with some amount. 
After login user can get the view of their portfolio. Ticket details and the amount invested on it. This service call the trading-service 
to get the ticker unit price detail real time.

trading-service : It's another Eureka client which provides real time unitprice details for a ticker. For this project the data is saved in database.
If ticker not found then it return 404 not found and registration-service handle that using hystrix circuit breaker.

All the api documentation can be found here:

registration-service : http://localhost:9090/registrationService/swagger-ui.html
trading-service      : http://localhost:9092/tradingService/swagger-ui.html
