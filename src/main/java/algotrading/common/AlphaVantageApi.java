package algotrading.common;

import algotrading.parsers.PV;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import static algotrading.parsers.PVParser.parseStringToPriceDataList;

@Service
public class AlphaVantageApi implements FileManager {
  HttpRequest request;
  HttpResponse<String> response;
  String uri;
  List<String> tickers = Constants.tickers;

  public List<List<PV>> getRawDataByTickers(String function, String interval, String compactOrNot)
      throws IOException, InterruptedException {
    List<List<PV>> ret = new ArrayList<>();
    for (String ticker : tickers) {
      ret.add(
          parseStringToPriceDataList(getDataByFunction(function, ticker, interval, compactOrNot)));
    }
    return ret;
  }

  public List<String> getTechnicalDataByTickers(
      String indicator, String interval, @Nullable String timePeriod)
      throws IOException, InterruptedException {
    List<String> ret = new ArrayList<>();
    for (String ticker : tickers) {
      ret.add(getDataByIndicator(indicator, ticker, interval, timePeriod));
    }
    return ret;
  }

  public String getDataByFunction(
      String function, String symbol, String interval, String compactOrNot)
      throws IOException, InterruptedException {

    request =
        HttpRequest.newBuilder()
            .uri(URI.create(constructUri(function, symbol, interval, compactOrNot)))
            .header("x-rapidapi-key", Constants.Headers.get("x-rapidapi-key"))
            .header("x-rapidapi-host", Constants.Headers.get("x-rapidapi-host"))
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();

    response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

    return response.body();
  }

  public String getDataByIndicator(
      String indicator, String symbol, String interval, @Nullable String timePeriod)
      throws IOException, InterruptedException {

    switch (indicator.toLowerCase()) {
      case "rsi":
        uri = constructUriByIndicator("RSI", symbol, interval, timePeriod, null);
        break;
      case "stoch":
        uri = constructUriByIndicator("STOCH", symbol, interval, null, "14");
        break;
      case "macd":
        uri = constructUriByIndicator("MACD", symbol, interval, null, null);
        break;
    }

    request =
        HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .header("x-rapidapi-key", Constants.Headers.get("x-rapidapi-key"))
            .header("x-rapidapi-host", Constants.Headers.get("x-rapidapi-host"))
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();

    response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

    return response.body();
  }

  private String constructUri(
      String function, String symbol, @Nullable String interval, @Nullable String compactOrNot) {
    return Constants.Base
        + addPadding("function", function)
        + addPadding("symbol", symbol)
        + addPadding("interval", interval)
        + addPadding("output size", compactOrNot)
        + Constants.DataType;
  }

  private String constructUriByIndicator(
      String function,
      String symbol,
      String interval,
      @Nullable String timePeriod,
      @Nullable String fastkperiod) {
    return Constants.Base
        + addPadding("function", function)
        + addPadding("symbol", symbol)
        + addPadding("interval", interval)
        + addPadding("time period", timePeriod)
        + "series_type=close&"
        + addPadding("fastkperiod", fastkperiod)
        + Constants.DataType;
  }

  private String addPadding(String param, String value) {
    if (value == null) return "";
    else {
      switch (param.toLowerCase()) {
        case "time period":
        case "series type":
          return param.replace(" ", "_") + '=' + value + '&';
        case "output size":
          return "output_size=" + (value.equalsIgnoreCase("compact") ? "compact" : "full") + '&';
        default:
          return param.replace(" ", "") + '=' + value + '&';
      }
    }
  }
}
