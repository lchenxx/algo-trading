package algotrading.common;

import org.springframework.lang.Nullable;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class AlphaVantageApi {
  HttpRequest request;
  HttpResponse<String> response;
  String uri;

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

    System.out.println("Request URI is " + request.uri());
    System.out.println(response.body());

    return response.body();
  }

  public String getDataByIndicator(
      String indicator, String symbol, String interval, @Nullable String timePeriod)
      throws IOException, InterruptedException {
    switch (indicator.toLowerCase()) {
      case "rsi":
        uri = constructUriByIndicator("RSI", symbol, interval, timePeriod, null);
      case "stoch":
        uri = constructUriByIndicator("STOCH", symbol, interval, null, "14");
      case "macd":
        uri = constructUriByIndicator("MACD", symbol, interval, null, null);
    }
    request =
        HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .header("x-rapidapi-key", Constants.Headers.get("x-rapidapi-key"))
            .header("x-rapidapi-host", Constants.Headers.get("x-rapidapi-host"))
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
    HttpResponse<String> response =
        HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println("Request URI is " + request.uri());
    System.out.println(response.body());

    return response.body();
  }

  private String constructUri(
      String function, String symbol, @Nullable String interval, @Nullable String compactOrNot) {
    return RequestUriBuilder.builder()
        .base(Constants.Base)
        .function(addPadding("function", function))
        .symbol(addPadding("symbol", symbol))
        .interval(addPadding("interval", interval))
        .outputSize(addPadding("output size", compactOrNot))
        .dataType(Constants.DataType)
        .build()
        .toString();
  }

  private String constructUriByIndicator(
      String function,
      String symbol,
      String interval,
      @Nullable String timePeriod,
      @Nullable String fastkperiod) {
    return RequestUriBuilder.builder()
        .base(Constants.Base)
        .function(addPadding("function", function))
        .symbol(addPadding("symbol", symbol))
        .interval(addPadding("interval", interval))
        .timePeriod(addPadding("time period", timePeriod))
        .series_type("series_type=close&")
        .fastkperiod(addPadding("fastkperiod", fastkperiod))
        .dataType(Constants.DataType)
        .build()
        .toString();
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
