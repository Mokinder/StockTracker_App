import 'package:flutter/material.dart';
import '../Repo/LoginRepo.dart';
import 'package:my_first_app/pages/Home.dart';
import 'package:my_first_app/pages/SignUp.dart';




//<-- Login screen -->
class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();

}

class _LoginPageState extends State<LoginPage> with SingleTickerProviderStateMixin {
  late TabController _tabController;

  final _formKey1 = GlobalKey<FormState>();
  final _formKey2 = GlobalKey<FormState>();

  final _nameController = TextEditingController();
  final _passwordController = TextEditingController();



  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 2, vsync: this);
  }


  @override
  void dispose() {
    _tabController.dispose();
    _nameController.dispose();
    _passwordController.dispose();
    super.dispose();
  }


  Loginrepo loginRepo = Loginrepo();


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Login"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(250.0),
        child: Form(
          key: _formKey1,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              TextFormField(
                controller: _nameController,
                decoration: InputDecoration(
                  labelText: 'Username',
                  contentPadding: EdgeInsets.symmetric(vertical: 10, horizontal: 20),
                ),
                keyboardType: TextInputType.name,
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter an username';
                  }
                  else if (value.trim().length < 4) {
                    return 'Name must be at min 4 characters';
                  }
                  else
                    print(_nameController);
                },
              ),
              TextFormField(
                controller: _passwordController,
                decoration: InputDecoration(
                    labelText: 'Password',
                    contentPadding: EdgeInsets.symmetric(vertical: 10, horizontal: 20),
                ),
                obscureText: true,
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter a password';
                  }
                  else if (value.length < 6) {
                    return 'Password must be at min 6 characters';
                  }
                  else if (!RegExp(r'[0-9]').hasMatch(value)) {
                    return 'Password must contain at min one number';
                  }
                  else
                  if (!RegExp(r'[!@#\$%^&*(),.?":{}|<>]').hasMatch(value)) {
                    return 'Password must contain at min one special character';
                  }
                  else
                    print(_passwordController);
                }),
              SizedBox(height: 20),
              ElevatedButton(
                onPressed: () async {
                  if (_formKey1.currentState?.validate() ?? false) {
                    // Show loading indicator
                    ScaffoldMessenger.of(context).showSnackBar(
                      SnackBar(content: Text('Verifying...'), backgroundColor: Colors.blueGrey),
                    );

                    String response = await loginRepo.verifyUser(
                      _nameController.text,
                      _passwordController.text,
                    );

                    if (response.toLowerCase().contains("login successful")) {
                      // Login success
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text('Login Successful!'), backgroundColor: Colors.green),
                      );

                      Navigator.pushAndRemoveUntil(
                        context,
                        MaterialPageRoute(builder: (context) => Home()),
                            (Route<dynamic> route) => false,
                      );
                    } else {
                      // Login failed
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text(response), backgroundColor: Colors.red),
                      );
                    }
                  }
                },
                child: Text('Login'),
              ),
              TextButton(
                onPressed: () {
                    // Navigate to the sign-up page
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => SignUp()),
                  );
                },
                child: Text('Don\'t have an account? Sign up'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}































