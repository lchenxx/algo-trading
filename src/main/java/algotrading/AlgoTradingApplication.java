package algotrading;

import algotrading.common.AlphaVantageApi;
import algotrading.parsers.SMAParser;

import java.io.IOException;

public class AlgoTradingApplication {
  public static void main(String[] args) throws IOException, InterruptedException {
    AlphaVantageApi api = new AlphaVantageApi();
    //        api.getDataByFunction("TIME_SERIES_INTRADAY", "AMZN", "5min", "compact");
    //        api.getRSIData("AMZN", "weekly", "10", "close");

    SMAParser parser = new SMAParser();
    parser.parseStringToSMAList(
        api.getDataByFunction("TIME_SERIES_INTRADAY", "AMZN", "5min", "compact"));
  }
}
