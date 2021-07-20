package algotrading.parsers;

import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ToString
public class RSIParser {

  public List<RSI> parseStringToRSIList(String input) {
    return Arrays.stream(input.replaceAll("[a-zA-Z]", "").substring(1).trim().split("\n"))
        .map(
            line ->
                RSI.builder()
                    .time(line.substring(0, 16))
                    .rsi(line.substring(17, line.length() - 1))
                    .build())
        .collect(Collectors.toList());
  }
}
