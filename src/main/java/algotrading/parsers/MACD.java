package algotrading.parsers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
public class MACD {
  public String time;
  public double macdHist;
}
