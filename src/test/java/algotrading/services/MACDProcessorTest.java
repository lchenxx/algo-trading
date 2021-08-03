package algotrading.services;

import algotrading.parsers.MACD;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MACDProcessorTest {

  private MACDProcessor processor = new MACDProcessor();

  MACDProcessorTest() throws IOException, InterruptedException {}

  @Test
  void processMACDHistAsBuy() throws IOException, InterruptedException {
    List<MACD> macdList = new ArrayList<>();
    macdList.add(MACD.builder().time("2021-07-26").macdHist(34.7753).build());
    macdList.add(MACD.builder().time("2021-07-23").macdHist(-3.1706).build());
    macdList.add(MACD.builder().time("2021-07-16").macdHist(-4.7753).build());
    macdList.add(MACD.builder().time("2021-07-09").macdHist(-5.7753).build());

    String result = processor.processMacdHist(macdList);
    assertEquals("buy", result);
  }

  @Test
  void processMACDHistAsBuy2() throws IOException, InterruptedException {
    List<MACD> macdList = new ArrayList<>();
    macdList.add(MACD.builder().time("2021-07-26").macdHist(34.7753).build());
    macdList.add(MACD.builder().time("2021-07-23").macdHist(33.1706).build());
    macdList.add(MACD.builder().time("2021-07-16").macdHist(32.7753).build());
    macdList.add(MACD.builder().time("2021-07-09").macdHist(-5.7753).build());

    String result = processor.processMacdHist(macdList);
    assertEquals("buy", result);
  }

  @Test
  void processMACDHistAsSell() throws IOException, InterruptedException {
    List<MACD> macdList = new ArrayList<>();
    macdList.add(MACD.builder().time("2021-07-26").macdHist(33.7753).build());
    macdList.add(MACD.builder().time("2021-07-23").macdHist(34.1706).build());
    macdList.add(MACD.builder().time("2021-07-16").macdHist(35.7753).build());
    macdList.add(MACD.builder().time("2021-07-09").macdHist(-5.7753).build());

    String result = processor.processMacdHist(macdList);
    assertEquals("sell", result);
  }

  @Test
  void processMACDHistAsNeutral() throws IOException, InterruptedException {
    List<MACD> macdList = new ArrayList<>();
    macdList.add(MACD.builder().time("2021-07-26").macdHist(33.7753).build());
    macdList.add(MACD.builder().time("2021-07-23").macdHist(33.1706).build());
    macdList.add(MACD.builder().time("2021-07-16").macdHist(35.7753).build());
    macdList.add(MACD.builder().time("2021-07-09").macdHist(34.7753).build());

    String result = processor.processMacdHist(macdList);
    assertEquals("neutral", result);
  }
}
