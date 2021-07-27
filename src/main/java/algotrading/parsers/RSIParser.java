package algotrading.parsers;

import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ToString
public class RSIParser {

  public List<RSI> parseStringToRSIList(String input) {
    return Arrays.stream(input.replaceAll("[a-zA-Z]", "").replaceFirst("^,\\s+", "").split("\n"))
        .map(
            line ->
                RSI.builder()
                    .time(line.substring(0, 10))
                    .rsi(line.substring(11, line.length() - 1))
                    .build())
        .collect(Collectors.toList());
  }
}
