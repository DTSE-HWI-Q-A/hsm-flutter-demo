import 'package:flutter/material.dart';
import 'package:flutterhsmdemoapp/widgets/gradient_back.dart';

class SignInScreen extends StatefulWidget {
  @override
  State createState() {
    return _SignInScreen();
  }
}

class _SignInScreen extends State<SignInScreen> {
  @override
  Widget build(BuildContext context) {
    return null;
  }

  Widget tokenScreen() {
    return Scaffold(
      body: Stack(
        alignment: Alignment.center,
        children: <Widget>[
          GradientBack("", null),
          Column(
            children: <Widget>[
              Text(
                "Welcome",
                style: TextStyle(
                    fontSize: 37.0,
                    color: Colors.white,
                    fontWeight: FontWeight.bold),
              )
            ],
          )
        ],
      ),
    );
  }
}
