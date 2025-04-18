
import 'dart:convert';
import 'dart:developer' as developer;

import 'package:http/http.dart' as http;

import '../Model/WatchList.dart';

class WatchListRepo{

  final String baseUrl = 'http://192.168.1.3:5545';

  final String username = 'Mokinder'; // Replace with the actual username
  final String password = 'Moki@1234'; // Replace with the actual password

  late final String basicAuth;

  WatchListRepo(){
    basicAuth = 'Basic ' + base64Encode(utf8.encode('$username:$password'));
  }


  Future<void> addItemToWatchList(String stock_name,String symbol, double price) async {
    final response = await http.post(
      Uri.parse('$baseUrl/api/stock-tracker/user/watch-list/add?symbol='+symbol+'&stockName='+stock_name),
      headers: {
        'Content-Type': 'application/json',
        'Authorization': basicAuth
      },
      body: json.encode({
        'symbol': symbol,
        'stock_name': stock_name,
        'price' : price,
      }),
    );

    print(response);
    developer.log('Response status code: ${response.statusCode}');
    developer.log('Response body: ${response.body}');

    if (response.statusCode == 201) {
      print("${response.body}");
      // Successfully added stock
    } else {
      print("Failed to add stock: ${response.statusCode}");
      print(response);
      // Handle error
    }
  }

  Future<List<WatchList>> getWatchlist() async {
    final response = await http.get(
      Uri.parse('$baseUrl/api/stock-tracker/user/watch-list/fetch-list'),
      headers: {
        'Content-Type': 'application/json',
        'Authorization': basicAuth
      },
    );

    print(response);
    developer.log('Response status code: ${response.statusCode}');
    developer.log('Response body: ${response.body}');

    if (response.statusCode == 200) {
      final List<dynamic> data = json.decode(response.body);
      return data.map((json) => WatchList.fromJson(json)).toList(); // ✅ Map to WatchList
    } else {
      throw Exception("Failed to fetch watchlist");
    }
  }

  Future<void> deleteFromWatchlist(int watchlist_id) async {
    final url = Uri.parse('$baseUrl/api/stock-tracker/user/watch-list/delete/$watchlist_id');
    final response = await http.delete(url,
      headers: {
      'Content-Type': 'application/json',
      'Authorization': basicAuth
    },);

    print('Deleting item with ID: $watchlist_id');

    print(response);
    developer.log('Response status code: ${response.statusCode}');
    developer.log('Response body: ${response.body}');

    if (response.statusCode != 200) {
      throw Exception("Failed to delete from watchlist");
    }
  }
}