

class StockList {
  final String symbol;
  final String name;
  final String exchange;
  final String country;

  StockList({required this.symbol, required this.name, required this.exchange, required this.country});

  factory StockList.fromJson(Map<String, dynamic> json) {
    return StockList(
      symbol: json['symbol'],
      name: json['companyName'],
      exchange: json['exchange'],
      country: json['country'],
     // price: (json['price'] as num).toDouble(),
    );
  }
}