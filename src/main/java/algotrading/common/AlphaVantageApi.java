package algotrading.common;

import org.springframework.lang.Nullable;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AlphaVantageApi {

  public String getDataByFunction(
      String function, String symbol, String interval, String compactOrNot)
      throws IOException, InterruptedException {
    function = "function=" + function.toUpperCase().replace(' ', '_') + '&';
    symbol = "symbol=" + symbol.toUpperCase() + '&';
    interval = interval.isEmpty() ? "" : "interval=" + interval + '&';
    compactOrNot = "output_size=" + (compactOrNot.equalsIgnoreCase("compact") ? "compact" : "full");

    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(
                URI.create(
                    "https://alpha-vantage.p.rapidapi.com/query?"
                        + function
                        + symbol
                        + interval
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

  public String getRSIData(String symbol, String interval, String timePeriod, String seriesType)
      throws IOException, InterruptedException {
    symbol = "symbol=" + symbol.toUpperCase() + "&";
    interval = interval == null ? "" : "interval=" + interval + "&";
    timePeriod = timePeriod == null ? "" : "time_period=" + timePeriod + "&";
    seriesType = "series_type=" + seriesType + "&";

    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(
                URI.create(
                    "https://alpha-vantage.p.rapidapi.com/query?function=RSI&"
                        + symbol
                        + interval
                        + timePeriod
                        + seriesType
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

  public String getSTOCHData(String symbol, String interval, @Nullable String fastKPeriod)
      throws IOException, InterruptedException {
    symbol = "symbol=" + symbol.toUpperCase() + "&";
    interval = interval == null ? "" : "interval=" + interval + "&";
    fastKPeriod = fastKPeriod == null ? "" : "fastkperiod=" + fastKPeriod + "&";

    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(
                URI.create(
                    "https://alpha-vantage.p.rapidapi.com/query?function=STOCH"
                        + symbol
                        + interval
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

  public String getMACDData(String symbol, String interval, String seriesType)
      throws IOException, InterruptedException {
    symbol = "symbol=" + symbol.toUpperCase() + "&";
    interval = interval == null ? "" : "interval=" + interval + "&";
    seriesType = "series_type=" + seriesType + "&";

    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(
                URI.create(
                    "https://alpha-vantage.p.rapidapi.com/query?function=MACD"
                        + symbol
                        + interval
                        + seriesType
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
