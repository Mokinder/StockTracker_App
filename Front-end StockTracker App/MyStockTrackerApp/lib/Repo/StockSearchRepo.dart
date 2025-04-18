import 'dart:convert';
import 'dart:developer' as developer;
import 'package:http/http.dart' as http;
import 'package:my_first_app/Model/Stock.dart';

class StockSearchRepo {

  final String username = 'Mokinder';
  final String password = 'Moki@1234';

  late final String basicAuth;

  StockSearchRepo(){
    basicAuth = 'Basic ' + base64Encode(utf8.encode('$username:$password'));
  }

  Future<List<Stock>> searchStocks(String query) async {

    final url = 'http://192.168.1.3:5545/api/stocks/search?query=' + query;

    final response = await http.get(
      Uri.parse(url),
      headers: <String, String>{
        'Authorization': basicAuth,
      },
    );

    print(response);
    developer.log('Response status code: ${response.statusCode}');
    developer.log('Response body: ${response.body}');

    try {
      if (response.statusCode == 302) {
        List jsonResponse = json.decode(response.body);
        return jsonResponse.map((data) => Stock.fromJson(data)).toList();
      } else {
        throw Exception('Failed to load stocks..');
      }
    } catch (e) {
      print(e);
      throw Exception('Failed to load stocks..');
    }
  }
}


