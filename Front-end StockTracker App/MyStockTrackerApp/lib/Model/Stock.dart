import 'package:flutter/material.dart';

class Stock {
  final String name;
  final String symbol;
  final double price;

  Stock({required this.name, required this.symbol, required this.price});

  factory Stock.fromJson(Map<String, dynamic> json) {
    return Stock(
      name: json['name'],
      symbol: json['symbol'],
      price: json['price']?.toDouble() ?? 0.0,
    );
  }
}