package algotrading.parsers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
public class SMA {
  String time;
  String sma;
}
