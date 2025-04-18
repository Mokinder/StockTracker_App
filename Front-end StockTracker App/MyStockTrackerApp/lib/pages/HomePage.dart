import 'package:flutter/material.dart';
import 'package:my_first_app/Repo/PortfolioRepo.dart';
import 'package:my_first_app/Model/Stock.dart';
import 'package:my_first_app/Repo/StockSearchRepo.dart';
import 'package:my_first_app/Repo/WatchListRepo.dart';
import 'package:my_first_app/pages/StockDetailPage.dart';



//<-- HomePage Tab -->
class Homepage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Center(
      child:
      SearchTab(),
    );
  }
}

class SearchTab extends StatefulWidget {
  @override
  _SearchTabState createState() => _SearchTabState();
}

class _SearchTabState extends State<SearchTab> {

  TextEditingController _searchController = TextEditingController();

  List<Stock> stocks = [];
  bool isLoading = false;
  String errorMessage = "";

  StockSearchRepo searchRepo = StockSearchRepo();

  search() async {
    setState(() {
      isLoading = true;
      errorMessage = "";
    });
    try {
      final results = await searchRepo.searchStocks(_searchController.text);
      setState(() {
        stocks = results;
        isLoading = false;
        errorMessage = "Got results...!!";
      });
    } catch (e) {
      setState(() {
        isLoading = false;
        errorMessage = 'Something went wrong. Please try again.';
      });
    }
  }
  // PortfolioRepo portfolioRepo = PortfolioRepo();
  WatchListRepo watchListRepo = WatchListRepo();



  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        // 🔍 Search Bar
        Padding(
          padding: const EdgeInsets.only(left: 250.0,right: 300.0,top: 10),
          child: TextField(
            controller: _searchController,
            decoration: InputDecoration(
              labelText: 'Search...',
              suffixIcon: IconButton(
                icon: Icon(Icons.search),
                onPressed: () async {
                  await search();},
              ),
            ),
          ),
        ),
        Expanded(
          child: isLoading
              ? Center(child: CircularProgressIndicator())
              : errorMessage.isNotEmpty && stocks.isEmpty
              ? Center(
            child: Text(
              errorMessage,
              style: TextStyle(color: Colors.red, fontSize: 16),
            ),
          )
              : stocks.isEmpty
              ? Center(child: Text('No results found.'))
              : ListView.builder(
                    itemCount: stocks.length,
                    itemBuilder: (context, index) {
                         final stock = stocks[index];
                          return ListTile(
                          title: Text(stock.name),
                          subtitle: Text('Symbol: ${stock.symbol}'),
                            onTap: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (_) => StockDetailPage(stock: stock),
                                ),
                              );
                              },
                          );
                          },
              ),
        ),
        // ElevatedButton(onPressed: () => portfolioRepo.createPortfolio(), child: Text("Portfolio creation")),
      ],
    );
  }
}



