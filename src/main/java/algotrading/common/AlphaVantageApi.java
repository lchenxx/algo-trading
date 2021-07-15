package algotrading.common;

import org.springframework.lang.Nullable;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AlphaVantageApi {

  public String getDataByFunction(
      String interval, String function, String symbol, String compactOrNot)
      throws IOException, InterruptedException {
    interval = interval.isEmpty() ? "" : "interval=" + interval;
    function = "function=" + function.toUpperCase().replace(' ', '_');
    symbol = "symbol=" + symbol.toUpperCase();
    compactOrNot = "output_size=" + (compactOrNot.equalsIgnoreCase("compact") ? "compact" : "full");

    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(
                URI.create(
                    "https://alpha-vantage.p.rapidapi.com/query?"
                        + interval
                        + '&'
                        + function
                        + '&'
                        + symbol
                        + "&datatype=csv&"
                        + compactOrNot))
            .header("x-rapidapi-key", "f5da0dee66msh4092453d2c85d7ap16c5c0jsn42ed0a42276c")
            .header("x-rapidapi-host", "alpha-vantage.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
    HttpResponse<String> response =
        HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println("Request URI is " + request.uri());
    System.out.println(response.body());

    return response.body();
  }

  public String getDataByTechnicalIndicator(
      @Nullable String interval,
      String seriesType,
      String function,
      String symbol,
      @Nullable String timePeriod,
      @Nullable String fastKPeriod)
      throws IOException, InterruptedException {
    interval = interval == null ? "" : "interval=" + interval + "&";
    seriesType = "series_type=" + seriesType + "&";
    function = "function=" + function.toUpperCase().replace(' ', '_') + "&";
    symbol = "symbol=" + symbol.toUpperCase() + "&";
    timePeriod = timePeriod == null ? "" : "time_period=" + timePeriod + "&";
    fastKPeriod = fastKPeriod == null ? "" : "fastkperiod=" + fastKPeriod + "&";

    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(
                URI.create(
                    "https://alpha-vantage.p.rapidapi.com/query?"
                        + interval
                        + seriesType
                        + function
                        + symbol
                        + timePeriod
                        + fastKPeriod
                        + "&datatype=csv"))
            .header("x-rapidapi-key", "f5da0dee66msh4092453d2c85d7ap16c5c0jsn42ed0a42276c")
            .header("x-rapidapi-host", "alpha-vantage.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
    HttpResponse<String> response =
        HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println("Request URI is " + request.uri());
    System.out.println(response.body());

    return response.body();
  }
}
