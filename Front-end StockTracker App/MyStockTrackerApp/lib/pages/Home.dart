import 'package:flutter/material.dart';
import 'package:my_first_app/pages/HomePage.dart';
import 'package:my_first_app/pages/LoginPage.dart';
import 'package:my_first_app/pages/Portfolio.dart';
import 'package:my_first_app/pages/WatchlistPage.dart';



void logout(BuildContext context) async {
  // Navigate to login screen (assuming you have a named route set up)
  Navigator.pushAndRemoveUntil(
    context,
    MaterialPageRoute(builder: (context) => LoginPage()),
        (Route<dynamic> route) => false,
  );
  print('User Logged Out');
}
//<-- Home screen -->
class Home extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false, //remove the debug tag//
      home: DefaultTabController(
        length: 3,
        child: Scaffold(
          appBar: AppBar(
            title: Row(
              children: [
                Image.asset(
                  '/Users/mokinders/IdeaProjects/my_first_app/images/stock_tracker_logo.jpeg',  // <-- Your logo image path
                  height: 50,
                ),
                SizedBox(width: 8),
                Text('StockTracker App'),
              ],
            ),
            backgroundColor: Colors.white60,
            actions: [
              IconButton(
                icon: Icon(Icons.logout),
                onPressed: () {
                  logout(context);
                },
              ),
            ],
            bottom: TabBar(
              tabs: [
                Tab(icon: Icon(Icons.home), text: 'Home'),
                Tab(icon: Icon(Icons.pages), text: 'Portfolio'),
                Tab(icon: Icon(Icons.watch_later), text: 'Watchlist'),
              ],
            ),
          ),
          body: TabBarView(
              children: [
                Homepage(),
                Portfolio(),
                WatchlistPage(),
              ],
            ),
        ),
      ),
    );
  }
}