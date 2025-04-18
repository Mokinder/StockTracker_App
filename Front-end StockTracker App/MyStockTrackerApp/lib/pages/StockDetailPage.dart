import 'dart:developer' as developer;

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:my_first_app/Model/Stock.dart';
import 'package:my_first_app/Model/WatchList.dart';
import 'dart:convert';
import 'package:intl/intl.dart'; // for date formatting
import '../Repo/WatchListRepo.dart';

class StockDetailPage extends StatefulWidget {
  final Stock stock;

  const StockDetailPage({required this.stock});


  @override
  _StockDetailPageState createState() => _StockDetailPageState();
}

class _StockDetailPageState extends State<StockDetailPage> {
  final WatchListRepo watchListRepo = WatchListRepo();
  bool isAdding = false;
  bool isAdded = false;
  bool isLoadingHistory = false;
  List<dynamic> priceHistory = [];

  @override
  void initState() {
    super.initState();
    _initFlow();
  }

  Future<void> _initFlow() async {
    await _ensureStockInDB();
    await fetchPriceHistory();
  }


  Future<void> fetchPriceHistory() async {
    setState(() {
      isLoadingHistory = true;
    });

    final symbol = widget.stock.symbol;
    final today = DateFormat('yyyy-MM-dd').format(DateTime.now());
    final startDate = DateFormat('yyyy-MM-dd').format(DateTime.now().subtract(Duration(days: 7)));
    final String username = 'Mokinder'; // Replace with the actual username
    final String password = 'Moki@1234'; // Replace with the actual password

    final String basicAuth;
    basicAuth = 'Basic ' + base64Encode(utf8.encode('$username:$password'));

    final url = Uri.parse(
        'http://192.168.1.3:5545/api/stocks/history?symbol=$symbol&startDate=$startDate&endDate=$today');

    try {
      final response = await http.get(url,
          headers: <String, String>{
        'Authorization': basicAuth,
          },);
      print(response);
      developer.log('Response status code: ${response.statusCode}');
      developer.log('Response body: ${response.body}');

      if (response.statusCode == 200) {
        final List<dynamic> history = json.decode(response.body);
        setState(() {
          priceHistory = history;
        });
      } else {
        print('Failed to load history: ${response.body}');
      }
    } catch (e) {
      print("Error fetching history: $e");
    } finally {
      setState(() {
        isLoadingHistory = false;
      });
    }
  }

  Future<void> _ensureStockInDB() async {
    final symbol = widget.stock.symbol;
    final String username = 'Mokinder'; // Replace with the actual username
    final String password = 'Moki@1234'; // Replace with the actual password
    final checkUrl = Uri.parse('http://192.168.1.3:5545/api/stocks/check?symbol=$symbol');
    final String basicAuth;
    basicAuth = 'Basic ' + base64Encode(utf8.encode('$username:$password'));

    try {
      final response = await http.get(checkUrl,
        headers: <String, String>{
        'Authorization': basicAuth,
      },);
    } catch (e) {
      print("Error in _ensureStockInDB: $e");
    }
  }


  Future<void> addToWatchlist() async {
    setState(() {
      isAdding = true;
    });

    try {
      await watchListRepo.addItemToWatchList(
        widget.stock.name,
        widget.stock.symbol,
        widget.stock.price,
      );

      setState(() {
        isAdded = true;
      });

      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('${widget.stock.name} added to Watchlist')),
      );
    } catch (e) {
      print("Error adding to watchlist: $e");

      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Failed to add to Watchlist')),
      );
    } finally {
      setState(() {
        isAdding = false;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    final stock = widget.stock;

    return Scaffold(
      appBar: AppBar(title: Text(stock.name)),
      body: Padding(
        padding: const EdgeInsets.all(20),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('Name: ${stock.name}', style: TextStyle(fontSize: 18)),
            SizedBox(height: 10),
            Text('Symbol: ${stock.symbol}', style: TextStyle(fontSize: 16)),
            SizedBox(height: 10),
            Text('Current Price: ₹${stock.price}', style: TextStyle(fontSize: 16)),
            SizedBox(height: 20),

            Text('Price History (Past 7 Days):',
                style: TextStyle(fontWeight: FontWeight.bold)),
            SizedBox(height: 8),
            Expanded(
              child: isLoadingHistory
                  ? Center(child: CircularProgressIndicator())
                  : priceHistory.isEmpty
                  ? Text("No history available.")
                  : ListView.builder(
                itemCount: priceHistory.length,
                itemBuilder: (context, index) {
                  final entry = priceHistory[index];
                  return ListTile(
                    title: Text(
                        '₹${(entry['close_price'] ?? 0).toDouble().toStringAsFixed(2)}'),
                    subtitle: Text(entry['date']),
                  );
                },
              ),
            ),
            Center(
              child: ElevatedButton.icon(
                onPressed: isAdding || isAdded ? null : addToWatchlist,
                icon: isAdding
                    ? SizedBox(
                    width: 18,
                    height: 18,
                    child: CircularProgressIndicator(
                        strokeWidth: 2, color: Colors.white))
                    : Icon(Icons.remove_red_eye),
                label:
                Text(isAdded ? "Added to Watchlist" : "Add to Watchlist"),
              ),
            ),
          ],
        ),
      ),
    );
  }
}



