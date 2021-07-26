package algotrading;

import algotrading.common.AlphaVantageApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class AlgoTradingApplication {
  public static void main(String[] args) throws IOException, InterruptedException {
    AlphaVantageApi api = new AlphaVantageApi();
    api.getRawDataByTickers("TIME_SERIES_DAILY", "5min", "compact");
    api.getTechnicalDataByTickers("RSI", "weekly", "10");
    //    api.getDataByFunction("TIME_SERIES_INTRADAY", "AMZN", "5min", "compact");
    //
    //    RSIParser rsiParser = new RSIParser();
    //    rsiParser.parseStringToRSIList(api.getDataByIndicator("RSI", "AMZN", "weekly", "10"));
    //
    //    STOCHParser stochParser = new STOCHParser();
    //    stochParser.parseStringToStochList(api.getDataByIndicator("STOCH", "AMZN", "weekly",
    // null));
    //
    //    MACDParser macdParser = new MACDParser();
    //    macdParser.parseStringToMacdList(api.getDataByIndicator("MACD", "AMZN", "weekly", null));
  }

  //  public static void main(String[] args){
  //    SpringApplication.run(AlgoTradingApplication.class, args);
  //  }
}
