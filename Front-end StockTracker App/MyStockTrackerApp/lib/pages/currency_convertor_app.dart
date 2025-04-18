import 'package:flutter/material.dart';

class CurrencyConvertorApp extends StatelessWidget{
  const CurrencyConvertorApp({super.key});

  @override
  Widget build(BuildContext context) {

    final border = OutlineInputBorder(
      borderSide: const BorderSide(
        color: Colors.blueGrey,
        width: 2.0,
        style: BorderStyle.solid,
      ),
      borderRadius: BorderRadius.all(Radius.circular(50),)
    );

    return Scaffold(
      backgroundColor: Colors.grey,
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
        //crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Text('Welcome to Home Page..!!',
            style: TextStyle(
              fontSize: 45,
              fontWeight: FontWeight.w400,
              fontStyle: FontStyle.italic,
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(left: 30,right: 50),
            child: TextField(
              style: TextStyle(
                // height: 1000,
                color: Colors.black87,
              ),
              decoration: InputDecoration(
                hintText: 'Enter the INR here',
                hintStyle: TextStyle(
                  color: Colors.grey,
                ),
                prefixIcon: Icon(Icons.currency_rupee),
                prefixIconColor: Colors.blue,
                filled: true,
                fillColor: Colors.white,
                focusedBorder: border,
                enabledBorder: border,
              ),
              keyboardType: TextInputType.numberWithOptions(decimal: true),
            ),
          ),
          TextButton(onPressed: (){
            print('Botton clicked.. Yet to convert it..!!');
          },
            style: const ButtonStyle(
              backgroundColor: MaterialStatePropertyAll(Colors.teal),
              foregroundColor: MaterialStatePropertyAll(Colors.white),
              maximumSize: MaterialStatePropertyAll(
                Size(double.infinity, 50),
              ),
            ),
            child:
            Text('Convert'),
          )
        ],),
      ),
    );
  }
}