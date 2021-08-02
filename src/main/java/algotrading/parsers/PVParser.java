package algotrading.parsers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PVParser {
  public static List<PV> parseStringToPriceDataList(String input) {
    return Arrays.stream(
            input.replaceAll("[a-zA-Z]", "").substring(5).replaceFirst("^\\s+", "").split("\n"))
        .map(
            line ->
                PV.builder()
                    .time(line.split(",")[0])
                    .price(line.split(",")[4])
                    .volume(line.split(",")[5].trim())
                    .build())
        .collect(Collectors.toList());
  }
}
