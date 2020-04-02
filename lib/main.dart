import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
      ),
      home: new MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => new _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const platform = const MethodChannel('demo.gawkat.com/info');

  String _message = "No messages yet...";

  @override
  void initState() {
    // fetch and change the message from the platform

    _getMessage().then((String message) {
      setState(() {
        _message = message;
      });
    });
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text("Home"),
      ),
      body: new ListView(
        children: <Widget>[
          new ListTile(
            title: new Text(_message),
          )
        ],
      ),
    );
  }

  Future<String> _getMessage() async {
    var sendMap = <String, dynamic>{
      'from': 'Brandon',
    };

    String value;

    try {
      value = await platform.invokeMethod('getMessage', sendMap);
    } catch (e) {
      print(e);
    }

    return value;
  }
}

