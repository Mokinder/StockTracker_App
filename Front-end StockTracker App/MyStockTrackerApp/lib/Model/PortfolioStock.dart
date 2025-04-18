

class PortfolioStock {
  final int holding_id;
  final String stock_name;
  final double purchase_price;
  final int quantity;
  final DateTime purchase_date;

  PortfolioStock({
    required this.holding_id,
    required this.stock_name,
    required this.purchase_price,
    required this.quantity,
    required this.purchase_date,
  });
}
