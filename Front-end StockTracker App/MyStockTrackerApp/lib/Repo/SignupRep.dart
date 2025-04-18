import 'dart:convert';
import 'package:http/http.dart' as http;

class SignupRepo {
  Future<http.Response> register(
      String username,String full_name,String email_id,String password) async {
    //create uri
    var uri = Uri.parse("http://localhost:5545/public/register-new");
    //header
    Map<String, String> headers = {"Content-Type": "application/json"};
    //body
    Map data = {
      'username': '$username',
      'full_name' : '$full_name',
      'email_id' : '$email_id',
      'password' : '$password',
    };
    //convert the above data into json
    var body = json.encode(data);
    var response = await http.post(uri, headers: headers, body: body);

    //print the response body
    print("${response.body}");

    return response;
  }
}
