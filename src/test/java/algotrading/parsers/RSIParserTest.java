package algotrading.parsers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RSIParserTest {

  @Test
  void extract() {
    String input = "time,RSI\r\n2021-07-26,65.5899\r\n2021-07-23,63.9996\r\n2021-07-16,60.8708\r\n";

    RSIParser parser = new RSIParser();
    List<RSI> rsiList = parser.parseStringToRSIList(input);

    assertEquals(3, rsiList.size());
    assertEquals("2021-07-26", rsiList.get(0).time);
    assertEquals("65.5899", rsiList.get(0).rsi);
    assertEquals("2021-07-23", rsiList.get(1).time);
    assertEquals("63.9996", rsiList.get(1).rsi);
    assertEquals("2021-07-16", rsiList.get(2).time);
    assertEquals("60.8708", rsiList.get(2).rsi);
  }
}
