package algotrading.parsers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static algotrading.parsers.PVParser.parseStringToPriceDataList;
import static org.junit.jupiter.api.Assertions.*;

class PVParserTest {

  @Test
  void extract() {
    String input =
        "timestamp,open,high,low,close,volume\r\n2021-07-30,3347.9500,3368.1400,3306.9800,3327.5900,9965593\r\n2021-07-29,3627.7500,3637.9500,3580.0100,3599.9200,4337611\r\n";
    List<PV> pvList = parseStringToPriceDataList(input);

    assertEquals(2, pvList.size());
    assertEquals("2021-07-30", pvList.get(0).time);
    assertEquals("3327.5900", pvList.get(0).price);
    assertEquals("9965593", pvList.get(0).volume);
    assertEquals("2021-07-29", pvList.get(1).time);
    assertEquals("3599.9200", pvList.get(1).price);
    assertEquals("4337611", pvList.get(1).volume);
  }
}
