package algotrading.parsers;

import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ToString
public class SMAParser {

  public List<SMA> parseStringToSMAList(String input) {
    return Arrays.stream(input.replaceAll("[a-zA-Z]", "").substring(1).trim().split("\n"))
        .map(
            line ->
                SMA.builder()
                    .date(line.substring(0, 16))
                    .sma(line.substring(17, line.length() - 1))
                    .build())
        .collect(Collectors.toList());
  }
}
