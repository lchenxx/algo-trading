package algotrading.parsers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
public class RSI {
  String time;
  String rsi;
}
