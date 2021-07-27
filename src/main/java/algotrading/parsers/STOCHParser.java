package algotrading.parsers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class STOCHParser {

  public List<STOCH> parseStringToStochList(String input) {
    return Arrays.stream(
            input.replaceAll("[a-zA-Z]", "").substring(4).replaceFirst("^\\s+", "").split("\n"))
        .map(
            line ->
                STOCH
                    .builder()
                    .time(line.split(",")[0])
                    .slowD(line.split(",")[1])
                    .slowK(line.split(",")[2].trim())
                    .build())
        .collect(Collectors.toList());
  }
}
