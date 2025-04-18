import 'package:flutter/material.dart';
import 'package:my_first_app/pages/LoginPage.dart';
import 'pages/currency_convertor_app.dart';




void main() => runApp(MyTabApp());

class MyTabApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: LoginPage(),
    );
  }
}




















//void main() {
// runApp(const MyApp());
//}

//void main(){
// runApp(const myApp());
//}

//class myApp extends StatelessWidget{
// const myApp({super.key});

//  @override
// Widget build(BuildContext context){
//return const Text('Welcome to Home Page..!!',textDirection: TextDirection.ltr,);
//  return MaterialApp(
//   home: CurrencyConvertorApp(),
// );
//}
//}
