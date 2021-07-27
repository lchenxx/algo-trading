package algotrading.parsers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MACDParserTest {

  @Test
  void parseStringToMacdList() {
    String input =
        "time,MACD,MACD_Hist,MACD_Signal\r\n2021-07-26,127.9534,34.7753,93.1780\r\n2021-07-23,117.6548,33.1706,84.4842\r\n2021-07-16,107.1952,31.0037,76.1916\r\n";
    MACDParser parser = new MACDParser();
    List<MACD> macdList = parser.parseStringToMacdList(input);

    assertEquals(3, macdList.size());
    assertEquals("2021-07-26", macdList.get(0).time);
    assertEquals("127.9534", macdList.get(0).macd);
    assertEquals("34.7753", macdList.get(0).macdHist);
    assertEquals("93.1780", macdList.get(0).macdSignal);
    assertEquals("2021-07-23", macdList.get(1).time);
    assertEquals("117.6548", macdList.get(1).macd);
    assertEquals("33.1706", macdList.get(1).macdHist);
    assertEquals("84.4842", macdList.get(1).macdSignal);
    assertEquals("2021-07-16", macdList.get(2).time);
    assertEquals("107.1952", macdList.get(2).macd);
    assertEquals("31.0037", macdList.get(2).macdHist);
    assertEquals("76.1916", macdList.get(2).macdSignal);
  }
}
