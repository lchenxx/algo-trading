package algotrading.parsers;

import lombok.ToString;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.json.JsonParser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ToString
public class SMAParser {

  public List<SMA> parseStringToSMAList(String input) {
    return Arrays.stream(input.replaceAll("[a-zA-Z]", "").substring(1).trim().split("\n"))
        .map(
            line ->
                SMA.builder()
                    .date(line.substring(0, 16))
                    .sma(line.substring(17, line.length() - 1))
                    .build())
        .collect(Collectors.toList());
  }
  //
  //  public JSONArray parseStringToSMAListJson(String input) throws ParseException {
  //    Object obj = new JSONParser().parse(input);
  //    JSONArray array = new JSONArray();
  //    array.add(obj);
  //    System.out.println(array);
  //    return array;
  //  }
}
