package algotrading.parsers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class STOCHParserTest {

  @Test
  void extract() {
    String input =
        "time,SlowD,SlowK\r\n2021-07-26,82.0988,79.9112\r\n2021-07-23,84.1342,81.5514\r\n2021-07-16,85.0007,84.8337\r\n";

    STOCHParser parser = new STOCHParser();
    List<STOCH> stochList = parser.parseStringToStochList(input);

    assertEquals(3, stochList.size());
    assertEquals("2021-07-26", stochList.get(0).time);
    assertEquals("82.0988", stochList.get(0).slowD);
    assertEquals("79.9112", stochList.get(0).slowK);
    assertEquals("2021-07-23", stochList.get(1).time);
    assertEquals("84.1342", stochList.get(1).slowD);
    assertEquals("81.5514", stochList.get(1).slowK);
    assertEquals("2021-07-16", stochList.get(2).time);
    assertEquals("85.0007", stochList.get(2).slowD);
    assertEquals("84.8337", stochList.get(2).slowK);
  }
}
