package AlgoTrading;

import AlgoTrading.apis.AlphaVantageApi;
import AlgoTrading.parsers.SMAParser;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class AlgoTradingApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        AlphaVantageApi api = new AlphaVantageApi();
//        api.getDataByFunction("5min", "TIME_SERIES intraday", "SOFI", "compact");
//        api.getDataByFunction("", "time series daily", "SOFI", "compact");
//        api.getDataByTechnicalIndicator("5min", "close", "SMA", "MSFT", "60");

        SMAParser parser = new SMAParser();
        parser.parseStringToSMAList(api.getDataByTechnicalIndicator("daily", "close", "SMA", "MSFT", "60"));
    }
}
