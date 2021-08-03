package algotrading.services;

import algotrading.common.AlphaVantageApi;
import algotrading.parsers.MACD;
import algotrading.parsers.MACDParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MACDProcessor {

  @Autowired
  private AlphaVantageApi api;
  @Autowired
  private MACDParser macdParser;

  public String processMacdHist(@Nullable List<MACD> dataSet) throws IOException, InterruptedException {
    dataSet = dataSet == null? macdParser
            .parseStringToMacdList(api.getDataByIndicator("MACD", "AMZN", "60min", null))
            .subList(0, 5) : dataSet;
    String ret;

    if (dataSet.get(0).macdHist > 0 && dataSet.get(1).macdHist < 0) {
      ret = "buy";
    } else if (dataSet.get(0).macdHist > dataSet.get(1).macdHist
        && dataSet.get(1).macdHist > dataSet.get(2).macdHist
        && dataSet.get(1).macdHist > 0) {
      ret = "buy";
    } else if (dataSet.get(0).macdHist > 0
        && dataSet.get(0).macdHist < dataSet.get(1).macdHist
        && dataSet.get(1).macdHist > 0
        && dataSet.get(1).macdHist < dataSet.get(2).macdHist) {
      ret = "sell";
    } else ret = "neutral";
    return ret;
  }
}
