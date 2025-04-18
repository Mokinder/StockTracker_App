import 'dart:developer' as developer;

import 'package:http/http.dart' as http;
import 'package:my_first_app/Model/PortfolioStock.dart';
import 'dart:convert';
import 'package:my_first_app/Model/PortfolioStockDisplay.dart';

class PortfolioStockRepo{

  final String baseUrl = 'http://192.168.1.3:5545';

  final String username = 'Mokinder'; // Replace with the actual username
  final String password = 'Moki@1234'; // Replace with the actual password

  late final String basicAuth;

  PortfolioStockRepo(){
    basicAuth = 'Basic ' + base64Encode(utf8.encode('$username:$password'));
  }


  Future<void> addStockToBackend(String stock_name, int quantity, double purchase_price, String purchase_date) async {
    final response = await http.post(
      Uri.parse('$baseUrl/api/stock-tracker/user/porfolio-holdings/addStock'),
      headers: {
        'Content-Type': 'application/json',
        'Authorization': basicAuth
      },
      body: json.encode({
        'stock_name': stock_name,
        'quantity': quantity,
        'purchase_price': purchase_price,
        'purchase_date': purchase_date,
      }),
    );

    print(response);
    print('Response status code: ${response.statusCode}');
    print('Response body: ${response.body}');

    if (response.statusCode == 201) {
      print("${response.body}");
      // Successfully added stock
    } else {
      print("Failed to add stock: ${response.statusCode}");
      print(response);
      // Handle error
    }
  }


  Future<List<PortfolioStockDisplay>> fetchAllStocks() async {

    final url = '$baseUrl/api/stock-tracker/user/porfolio-holdings/all-holdings';
    final response = await http.get(
      Uri.parse(url),
      headers: <String, String>{
        'Authorization': basicAuth,
      },
    );

    print(response);
    developer.log('Response status code: ${response.statusCode}');
    developer.log('Response body: ${response.body}');

    if (response.statusCode == 200) {
      final List<dynamic> data = json.decode(response.body);
      return data.map((json) => PortfolioStockDisplay.fromJson(json)).toList();
    } else {
      throw Exception('Failed to load stocks');
    }
  }


  Future<void> updateStock(int holding_id, String stock_name, int quantity, double purchase_price, String purchase_date) async {
    final response = await http.put(
      Uri.parse('$baseUrl/api/stock-tracker/user/porfolio-holdings/update-stock'),
      headers: {
        'Content-Type': 'application/json',
        'Authorization': basicAuth
      },
      body: json.encode({
        'holding_id' : holding_id,
        'stock_name': stock_name,
        'quantity': quantity,
        'purchase_price': purchase_price,
        'purchase_date': purchase_date,
      }),
    );

    print(response);
    developer.log('Response status code: ${response.statusCode}');
    developer.log('Response body: ${response.body}');

    if (response.statusCode == 200) {
      print("${response.body}");
      // Successfully added stock
    } else {
      print("Failed to update the stock details: ${response.statusCode}");
      print(response);
      // Handle error
    }
  }

  Future<void> deleteStock(int holding_id) async {
    final response = await http.delete(
      Uri.parse('$baseUrl/api/stock-tracker/user/porfolio-holdings/delete-stock/$holding_id'),
      headers: {'Authorization': basicAuth},
    );

    print(response);
    developer.log('Response status code: ${response.statusCode}');
    developer.log('Response body: ${response.body}');

    if (response.statusCode == 200) {
      print("Deleted the stock..!!");
    }else{
      throw Exception('Failed to delete stock');
    }
  }
}