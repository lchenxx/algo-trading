package algotrading.services;

import algotrading.parsers.MACD;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MACDProcessorTest {

  private MACDProcessor processor = new MACDProcessor();

  @Test
  void processMACDHistAsBuy() {
    List<MACD> macdList = new ArrayList<>();
    macdList.add(MACD.builder().time("2021-07-26").macdHist(34.0000).build());
    macdList.add(MACD.builder().time("2021-07-23").macdHist(-3.0000).build());
    macdList.add(MACD.builder().time("2021-07-16").macdHist(-4.0000).build());
    macdList.add(MACD.builder().time("2021-07-09").macdHist(-5.0000).build());

    String result = processor.processMacdHist(macdList);
    assertEquals("buy", result);
  }

  @Test
  void processMACDHistAsBuy2() {
    List<MACD> macdList = new ArrayList<>();
    macdList.add(MACD.builder().time("2021-07-26").macdHist(34.0000).build());
    macdList.add(MACD.builder().time("2021-07-23").macdHist(33.0000).build());
    macdList.add(MACD.builder().time("2021-07-16").macdHist(32.0000).build());
    macdList.add(MACD.builder().time("2021-07-09").macdHist(-5.0000).build());

    String result = processor.processMacdHist(macdList);
    assertEquals("buy", result);
  }

  @Test
  void processMACDHistAsSell() {
    List<MACD> macdList = new ArrayList<>();
    macdList.add(MACD.builder().time("2021-07-26").macdHist(33.0000).build());
    macdList.add(MACD.builder().time("2021-07-23").macdHist(34.0000).build());
    macdList.add(MACD.builder().time("2021-07-16").macdHist(35.0000).build());
    macdList.add(MACD.builder().time("2021-07-09").macdHist(36.0000).build());
    macdList.add(MACD.builder().time("2021-07-09").macdHist(35.0000).build());

    String result = processor.processMacdHist(macdList);
    assertEquals("sell", result);
  }

  @Test
  void processMACDHistAsNeutral() {
    List<MACD> macdList = new ArrayList<>();
    macdList.add(MACD.builder().time("2021-07-26").macdHist(33.0000).build());
    macdList.add(MACD.builder().time("2021-07-23").macdHist(33.0000).build());
    macdList.add(MACD.builder().time("2021-07-16").macdHist(35.0000).build());
    macdList.add(MACD.builder().time("2021-07-09").macdHist(34.0000).build());

    String result = processor.processMacdHist(macdList);
    assertEquals("neutral", result);
  }
}
