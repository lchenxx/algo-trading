package algotrading.parsers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MACDParser {
  public List<MACD> parseStringToMacdList(String input) {
    return Arrays.stream(input.replaceAll("[a-zA-Z]", "").substring(5).trim().split("\n"))
        .map(
            line ->
                MACD.builder()
                    .time(line.split(",")[0])
                    .macd(line.split(",")[1])
                    .macdHist(line.split(",")[2])
                    .macdSignal(line.split(",")[3].trim())
                    .build())
        .collect(Collectors.toList());
  }
}
