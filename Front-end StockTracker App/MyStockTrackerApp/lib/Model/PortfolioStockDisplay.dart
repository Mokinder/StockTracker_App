

class PortfolioStockDisplay{
  final int holding_id;
  final String stock_name;
  final int quantity;
  final double purchase_price;
  final DateTime purchase_date;

  PortfolioStockDisplay({
    required this.holding_id,
    required this.stock_name,
    required this.purchase_price,
    required this.quantity,
    required this.purchase_date,
  });

  factory PortfolioStockDisplay.fromJson(Map<String, dynamic> json) {
    return PortfolioStockDisplay(
      holding_id: json['holding_id'],
      stock_name: json['stock_name'],
      quantity: json['quantity'],
      purchase_price: json['purchase_price'].toDouble(),
      purchase_date: DateTime.parse(json['purchase_date'].toString()),
    );
  }
}