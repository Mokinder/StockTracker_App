
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:my_first_app/Model/PortfolioStockDisplay.dart';
import 'dart:convert';

import 'package:my_first_app/Repo/PortfolioStockRepo.dart';



class PortfolioHolding extends StatefulWidget {
  @override
  State<PortfolioHolding> createState() => _PortfolioHoldingState();
}

class _PortfolioHoldingState extends State<PortfolioHolding> {
  final repo = PortfolioStockRepo();
  List<PortfolioStockDisplay> stocks = [];
  int? selectedId;

  @override
  void initState() {
    super.initState();
    loadStocks();
  }

  Future<void> loadStocks() async {
    final data = await repo.fetchAllStocks();
    setState(() {
      stocks = data;   // Refresh list or update UI
    });
  }

  void _showStockDialog(BuildContext context,{PortfolioStockDisplay? stock}) async {
    final nameCtrl = TextEditingController(text: stock?.stock_name);
    final qtyCtrl = TextEditingController(text: stock?.quantity.toString());
    final priceCtrl = TextEditingController(text: stock?.purchase_price.toString());
    final dateCtrl = TextEditingController(text: stock?.purchase_date.toString());

    await showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: Text(stock == null ? "Add Stock" : "Edit Stock"),
        content: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            TextField(controller: nameCtrl, decoration: InputDecoration(labelText: 'Stock Name')),
            TextField(controller: qtyCtrl, decoration: InputDecoration(labelText: 'Quantity'), keyboardType: TextInputType.number),
            TextField(controller: priceCtrl, decoration: InputDecoration(labelText: 'Brought Price'), keyboardType: TextInputType.number),
            TextField(
              controller: dateCtrl,
              decoration: InputDecoration(labelText: 'Purchase Date (yyyy-mm-dd)'),
              onTap: () async {
                FocusScope.of(context).requestFocus(FocusNode());
                DateTime? picked = await showDatePicker(
                  context: context,
                  initialDate: DateTime.now(),
                  firstDate: DateTime(2000),
                  lastDate: DateTime(2101),
                );
                if (picked != null) {
                  dateCtrl.text = picked.toIso8601String().split('T').first;
                }
              },
            ),
          ],
        ),
        actions: [
          TextButton(
            child: Text("Close"),
            onPressed: () {
              Navigator.pop(context);
            },
          ),
          TextButton(
            onPressed: () async {
              try {
                if (stock == null) {
                  repo.addStockToBackend(
                      nameCtrl.text, int.parse(qtyCtrl.text), double.parse(priceCtrl.text), dateCtrl.text);
                } else {
                  repo.updateStock(
                      stock.holding_id, nameCtrl.text, int.parse(qtyCtrl.text), double.parse(priceCtrl.text), dateCtrl.text);
                }
                //if (!mounted) return;

                Navigator.pop(context);
                await loadStocks();
              } catch (e) {
                if (!mounted) return;

                ScaffoldMessenger.of(context).showSnackBar(
                  SnackBar(content: Text("Failed to save stock")),
                );
              }
            },
            child: Text("Save"),
          )
        ],
      ),
    );
    await loadStocks();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Stock Portfolio')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            Row(
              children: [
                ElevatedButton.icon(
                  onPressed: () => _showStockDialog(context),
                  icon: Icon(Icons.add),
                  label: Text("Add Holdings"),
                ),
                const SizedBox(width: 10),
                ElevatedButton.icon(
                  onPressed: selectedId != null
                      ? () => _showStockDialog(context,
                      stock: stocks.firstWhere((s) => s.holding_id == selectedId))
                      : null,
                  icon: Icon(Icons.edit),
                  label: Text("Edit"),
                ),
                const SizedBox(width: 10),
                ElevatedButton.icon(
                  onPressed: selectedId != null
                      ? () async {
                    try {
                      await repo.deleteStock(selectedId!);
                      if (!mounted) return;
                      setState(() {
                        selectedId = null;
                      });
                      await loadStocks(); // This will refresh the list
                    } catch (e) {
                      print("Failed to delete stock: $e");
                      if (!mounted) return;
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text("Failed to delete stock")),
                      );
                    }
                  }
                      : null,
                  icon: Icon(Icons.delete),
                  label: Text("Delete"),
                  style: ElevatedButton.styleFrom(backgroundColor: Colors.red),
                ),
              ],
            ),
            const SizedBox(height: 20),
            Expanded(
              child: SingleChildScrollView(
                scrollDirection: Axis.horizontal,
                child: DataTable(
                  columns: const [
                    DataColumn(label: Text("Name")),
                    DataColumn(label: Text("Quantity")),
                    DataColumn(label: Text("Price")),
                    DataColumn(label: Text("Date")),
                  ],
                  rows: stocks.map((stock) {
                    return DataRow(
                      selected: stock.holding_id == selectedId,
                      onSelectChanged: (_) {
                        setState(() => selectedId = stock.holding_id);
                      },
                      cells: [
                        DataCell(Text(stock.stock_name)),
                        DataCell(Text(stock.quantity.toString())),
                        DataCell(Text(stock.purchase_price.toString())),
                        DataCell(Text(stock.purchase_date.toIso8601String())),
                      ],
                    );
                  }).toList(),
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}