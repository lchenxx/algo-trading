package algotrading.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
public class RequestUriBuilder {
  String base;
  String function;
  String symbol;
  String interval;
  String adjusted;
  String outputSize;
  String dataType;
  String timePeriod;
  String series_type;
  String fastkperiod;
  String slowkmatype;
  String slowdmatype;
}
