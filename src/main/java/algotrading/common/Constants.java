package algotrading.common;

import java.util.HashMap;
import java.util.Map;

public class Constants {
  static final String Base = "https://alpha-vantage.p.rapidapi.com/query?";
  static final String DataType = "datatype=csv";
  static final Map<String, String> Headers =
      new HashMap<>() {
        {
          put("x-rapidapi-key", "f5da0dee66msh4092453d2c85d7ap16c5c0jsn42ed0a42276c");
          put("x-rapidapi-host", "alpha-vantage.p.rapidapi.com");
        }
      };
}
