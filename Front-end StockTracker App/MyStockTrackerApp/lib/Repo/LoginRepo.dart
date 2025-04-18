import 'dart:convert';
import 'dart:developer' as developer;
import 'package:http/http.dart' as http;

class Loginrepo {
  Future<String> verifyUser(String username, String password) async {
    //create uri
    var uri = Uri.parse("http://localhost:5545/public/login");
    //header
    Map<String, String> headers = {"Content-Type": "application/json"};
    //body
    Map data = {
      'username': '$username',
      'password' : '$password',
    };
    //convert the above data into json
    var body = json.encode(data);

    try {
      final response = await http.post(uri, headers: headers, body: body);

      developer.log('Response status code: ${response.statusCode}');
      developer.log('Response body: ${response.body}');

      if (response.statusCode == 202) {
        return  "Login successful :${response.body}";
      } else if (response.statusCode == 401) {
        return "Invalid username or password.";
      } else if (response.statusCode == 404) {
        return "User not found.";
      } else {
        return "Server error: ${response.statusCode}";
      }
    } catch (e) {
      // Handle network or parsing errors
      return "An error occurred: $e";
    }


  }
}
