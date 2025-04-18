import 'dart:convert';
import 'dart:developer' as developer;
import 'package:http/http.dart' as http;
import '../Model/Users.dart';


class UserRepo {
  Future<List<Users>> fetchUsers() async {
    final String username = 'Mokinder'; // Replace with the actual username
    final String password = 'Moki@1234'; // Replace with the actual password

    final String basicAuth = 'Basic ' + base64Encode(utf8.encode('$username:$password'));

    var uri = Uri.parse("http://localhost:5545/stock-tracker/user/all");
    final response = await http.get(uri,
        headers: <String, String>{
      'Authorization': basicAuth,
    });

    developer.log('Response status code: ${response.statusCode}');
    developer.log('Response body: ${response.body}');

    if (response.statusCode == 200) {
      final List<dynamic> userListJson = jsonDecode(response.body);
      return userListJson.map((json) => Users.fromJson(json)).toList();
    } else {
      throw Exception('Failed to load users. Status code: ${response.statusCode}');
    }
  }
}


