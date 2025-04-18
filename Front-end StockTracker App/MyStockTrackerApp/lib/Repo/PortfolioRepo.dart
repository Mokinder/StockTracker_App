
import 'dart:convert';
import 'dart:developer' as developer;
import 'package:http/http.dart' as http;


class PortfolioRepo{

  final String username = 'Mokinder'; // Replace with the actual username
  final String password = 'Moki@1234'; // Replace with the actual password

  late final String basicAuth;

  PortfolioRepo(){
    basicAuth = 'Basic ' + base64Encode(utf8.encode('$username:$password'));
  }

  Future<http.Response> createPortfolio() async {

    var uri = Uri.parse("http://localhost:5545/stock-tracker/user/create-portfolio");
    final response = await http.get(uri,
        headers: <String, String>{
          'Authorization': basicAuth,
        });

    developer.log('Response status code: ${response.statusCode}');
    developer.log('Response body: ${response.body}');
    //print the response body

    return response;
  }

}