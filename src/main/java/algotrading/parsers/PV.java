package algotrading.parsers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
public class PV {
  String time;
  String price;
  String volume;
}
