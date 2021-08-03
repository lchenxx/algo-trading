package algotrading.parsers;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MACDParser {
  public List<MACD> parseStringToMacdList(String input) {
    return Arrays.stream(
            input.replaceAll("[a-zA-Z]", "").substring(5).replaceFirst("^\\s+", "").split("\n"))
        .map(
            line ->
                MACD.builder()
                    .time(line.split(",")[0])
                    .macdHist(Double.parseDouble(line.split(",")[2]))
                    .build())
        .collect(Collectors.toList());
  }
}
