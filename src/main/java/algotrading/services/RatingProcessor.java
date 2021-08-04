package algotrading.services;

import algotrading.common.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * the rating processor will aggregate recommendations from all technical indicators to provide a
 * final view on each ticker and output a JSON string
 */
public class RatingProcessor {

  private static final Logger log = LoggerFactory.getLogger(RatingProcessor.class);
  private final MACDProcessor macdProcessor;
  String buy = "buy";
  String sell = "sell";
  String neutral = "neutral";

  public RatingProcessor() {
    this.macdProcessor = new MACDProcessor();
  }

  public String processRating() throws IOException, InterruptedException {
    Map<String, String> map = new HashMap<>();
    Map<String, String> macdMap = macdProcessor.processEachTickerForMacd();

    for (String ticker : Constants.tickers) {
      String recommendation = macdMap.get(ticker);
      if (recommendation.equalsIgnoreCase(buy)) {
        log.info("aggregate recommendation for " + ticker + " is buy");
        map.put(ticker, buy);
      } else if (recommendation.equalsIgnoreCase(sell)) {
        log.info("aggregate recommendation for " + ticker + " is sell");
        map.put(ticker, sell);
      } else {
        log.info("aggregate recommendation for " + ticker + " is neutral");
        map.put(ticker, neutral);
      }
    }

    ObjectMapper mapper = new ObjectMapper();
    System.out.println(mapper.writeValueAsString(map));

    return mapper.writeValueAsString(map);
  }
}
