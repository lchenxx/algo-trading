package AlgoTrading.parsers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@AllArgsConstructor
public class SMA {
    String date;
    String sma;
}
