package algotrading.parsers;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class SMA {
  String date;
  String sma;
}
