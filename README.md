### Algo Trading App
#### About
This app is aimed to provide buy, sell or neutral recommendations for individual
US shares. An list of share tickers
will be provided in a file. The app reads the tickers from the file and retrieves the
share data
from [Alpha Vanage API](https://rapidapi.com/alphavantage/api/alpha-vantage/),
parses and processes the data. Then
output buy/sell/hold recommendations for individual share.
The application will be run as part of a pipeline twice a day during US stock
market opening period and recommendations
of each share will be email to stakeholders.
#### Technology
* Java 11
* Maven
* Spring boot
* pipeline details to be provided