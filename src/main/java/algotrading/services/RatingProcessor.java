package algotrading.services;

import algotrading.common.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * the rating processor will aggregate recommendations from all technical indicators to provide a
 * final view on each ticker and output a JSON string
 */
public class RatingProcessor {

  private MACDProcessor macdProcessor;

  public RatingProcessor() {
    this.macdProcessor = new MACDProcessor();
  }

  public String processRating() throws IOException, InterruptedException {
    Map<String, String> map = new HashMap<>();
    Map<String, String> macdMap = macdProcessor.processEachTickerForMacd();

    for (String ticker : Constants.tickers) {
      String recommendation = macdMap.get(ticker);
      if (recommendation.equalsIgnoreCase("buy")) map.put(ticker, "buy");
      else if (recommendation.equalsIgnoreCase("sell")) map.put(ticker, "sell");
      map.put(ticker, "neutral");
      ;
    }

    ObjectMapper mapper = new ObjectMapper();
    System.out.println(mapper.writeValueAsString(map));

    return mapper.writeValueAsString(map);
  }
}
