package algotrading.services;

import algotrading.common.AlphaVantageApi;
import algotrading.common.Constants;
import algotrading.parsers.MACD;
import algotrading.parsers.MACDParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the MACD processor will process MACD values for individual ticker and provide recommendation for
 * each
 */
@Service
public class MACDProcessor {

  private final AlphaVantageApi api;
  private final MACDParser macdParser;
  List<MACD> dataSet;

  public MACDProcessor() {
    this.api = new AlphaVantageApi();
    this.macdParser = new MACDParser();
  }

  public String processMacdHist(List<MACD> data) {
    String ret;
    if (data.get(0).macdHist > 0 && data.get(1).macdHist < 0) {
      ret = "buy";
    } else if (data.get(0).macdHist > data.get(1).macdHist
        && data.get(1).macdHist > data.get(2).macdHist
        && data.get(2).macdHist > 0) {
      ret = "buy";
    } else if (data.get(0).macdHist > 0
        && data.get(0).macdHist < data.get(1).macdHist
        && data.get(1).macdHist < data.get(2).macdHist
        && data.get(2).macdHist < data.get(3).macdHist) {
      ret = "sell";
    } else ret = "neutral";

    return ret;
  }

  public Map<String, String> processEachTickerForMacd() throws IOException, InterruptedException {
    Map<String, String> map = new HashMap<>();
    for (String ticker : Constants.tickers) {
      dataSet =
          macdParser
              .parseStringToMacdList(api.getDataByIndicator("MACD", ticker, "60min", null))
              .subList(0, 5);
      map.put(ticker, processMacdHist(dataSet));
    }
    return map;
  }
}
