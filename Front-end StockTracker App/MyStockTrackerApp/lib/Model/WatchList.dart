

class WatchList{
  final int watchlist_item_id;
  final String stock_name;
  final String symbol;
  final double price;

  WatchList({
    required this.stock_name,
    required this.price,
    required this.symbol,
    required this.watchlist_item_id });

  factory WatchList.fromJson(Map<String, dynamic> json) {
    return WatchList(
      watchlist_item_id: json['watchlist_item_id'] ?? 0,
      stock_name: json['stock_name'] ?? '',
      symbol: json['symbol'] ?? '',
      price: (json['price'] ?? 0).toDouble(),
    );
  }
}