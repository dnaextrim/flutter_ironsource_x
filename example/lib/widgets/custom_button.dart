import 'package:flutter/material.dart';

class CustomButton extends StatelessWidget {
  final String label;
  final void Function()? onPressed;

  const CustomButton({Key? key, required this.label, this.onPressed})
      : super(key: key);
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.symmetric(vertical: 10.0),
      child: MaterialButton(
        minWidth: 250.0,
        height: 50.0,
        shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(40.0),
            side: const BorderSide(width: 2.0, color: Colors.blue)),
        child: Text(
          label.toUpperCase(),
          style: const TextStyle(),
        ),
        onPressed: onPressed,
      ),
    );
  }
}
