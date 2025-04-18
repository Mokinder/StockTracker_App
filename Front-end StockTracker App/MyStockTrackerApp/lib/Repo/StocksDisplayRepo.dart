
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:my_first_app/Model/StockList.dart';

class StockDataDisplayRepo{

  Future<List<StockList>> fetchStockdata() async {
    final response = await http.get(Uri.parse('http://<your-ip>:8080/api/stocks/watchlist'));

    if (response.statusCode == 200) {
      List jsonResponse = json.decode(response.body);
      return jsonResponse.map((data) => StockList.fromJson(data)).toList();
    } else {
      throw Exception('Failed to load stocks');
    }
  }

}