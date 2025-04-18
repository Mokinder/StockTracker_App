import 'package:flutter/material.dart';
import 'package:my_first_app/Model/WatchList.dart';
import 'package:my_first_app/Repo/WatchListRepo.dart';

//<-- WatchList Tab -->

class WatchlistPage extends StatefulWidget {
  @override
  _WatchlistPageState createState() => _WatchlistPageState();
}

class _WatchlistPageState extends State<WatchlistPage> {
  final watchlistrepo = WatchListRepo();
  List<WatchList> watchlist = [];
  Set<int> selectedIds = {};
  bool isLoading = true;

  @override
  void initState() {
    super.initState();
    fetchWatchlist();
  }

  Future<void> fetchWatchlist() async {
    try {
      final results = await watchlistrepo.getWatchlist();
      setState(() {
        watchlist = results;
        isLoading = false;
      });
    } catch (e) {
      setState(() => isLoading = false);
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text("Failed to load watchlist")),
      );
    }
  }

  void deleteSelected() async {
    try {
      for (var id in selectedIds) {
        await watchlistrepo.deleteFromWatchlist(id);
      }
      setState(() {
        selectedIds.clear();
      });
      await fetchWatchlist();
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text("Failed to delete selected items")),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Watchlist")),
      body: isLoading
          ? Center(child: CircularProgressIndicator())
          : Column(
        children: [
          if (selectedIds.isNotEmpty)
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: ElevatedButton.icon(
                icon: Icon(Icons.delete),
                label: Text("Delete Selected"),
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.red,
                ),
                onPressed: deleteSelected,
              ),
            ),
          Expanded(
            child: ListView.builder(
              itemCount: watchlist.length,
              itemBuilder: (context, index) {
                final stock = watchlist[index];
                final isSelected = selectedIds.contains(stock.watchlist_item_id);

                return CheckboxListTile(
                  value: isSelected,
                  onChanged: (bool? selected) {
                    setState(() {
                      if (selected == true) {
                        selectedIds.add(stock.watchlist_item_id);
                      } else {
                        selectedIds.remove(stock.watchlist_item_id);
                      }
                    });
                  },
                  title: Text(stock.stock_name),
                  subtitle: Text('${stock.symbol} - ₹${stock.price.toStringAsFixed(2)}'),
                  secondary: Icon(Icons.star_border),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}



