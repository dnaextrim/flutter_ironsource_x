import 'package:flutter/material.dart';

class BannerProvider extends ChangeNotifier {
  bool _isBannerShow = false;

  bool get isBannerShow => _isBannerShow;

  set isBannerShow(bool value) {
    _isBannerShow = value;
    notifyListeners();
  }
}
