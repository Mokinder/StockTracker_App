import 'package:flutter/material.dart';
import 'package:my_first_app/Repo/SignupRep.dart';
import 'package:my_first_app/pages/LoginPage.dart';


//<-- SignUp screen -->
class SignUp extends StatefulWidget {
  @override
  _SignUpState createState() => _SignUpState();

}

class _SignUpState extends State<SignUp> with SingleTickerProviderStateMixin {
  late TabController _tabController;

  final _formKey1 = GlobalKey<FormState>();
 // final _formKey2 = GlobalKey<FormState>();

  final _nameController = TextEditingController();
  final _fullname = TextEditingController();
  final _emailController = TextEditingController();
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
    _fullname.dispose();
    _emailController.dispose();
    super.dispose();
  }

  SignupRepo signupRepo = SignupRepo();


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("SignUp"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(200.0),
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
                controller: _fullname,
                decoration: InputDecoration(
                  labelText: 'Full Name',
                  contentPadding: EdgeInsets.symmetric(vertical: 10, horizontal: 20),
                ),
                keyboardType: TextInputType.name,
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter user full name';
                  }
                  else if (value.trim().length < 6) {
                    return 'Name must be at min 6 characters';
                  }
                  else
                    print(_fullname);
                },
              ),
              TextFormField(
                controller: _emailController,
                decoration: InputDecoration(
                  labelText: 'Email_id',
                  contentPadding: EdgeInsets.symmetric(vertical: 10, horizontal: 20),
                ),
                keyboardType: TextInputType.emailAddress,
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter your email-id';
                  }
                  else if (value.length < 6) {
                    return 'Email must be at min 6 characters';
                  }
                  else if (!value.contains('@')) {
                    return 'Email must contain "@" symbol';
                  }
                  else
                    print(_emailController);
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
                  else if (!RegExp(r'[!@#\$%^&*(),.?":{}|<>]').hasMatch(value)) {
                    return 'Password must contain at min one special character';
                  }
                  else
                    print(_passwordController);
                },
              ),
              SizedBox(height: 30),
              ElevatedButton(
                onPressed: () {
                  signupRepo.register(_nameController.text,_fullname.text,_emailController.text,_passwordController.text );
                  if (_formKey1.currentState?.validate() ?? false) {
                    // Handle sign-up logic
                    ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text('Signing up...')));
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => LoginPage()),);
                  }
                },
                child: Text('SignUp'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}