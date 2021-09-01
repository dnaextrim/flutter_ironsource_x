class IronSourceError {
  final String? errorMessage;
  final int? errorCode;

  IronSourceError({this.errorMessage, this.errorCode});
}

class Placement {
  final String? placementName, rewardName;
  final int? placementId, rewardAmount;

  Placement(
      {this.placementName,
      this.rewardName,
      this.placementId,
      this.rewardAmount});
}

class OfferwallCredit {
  final int? credits, totalCredits;
  final bool? totalCreditsFlag;

  OfferwallCredit({this.credits, this.totalCredits, this.totalCreditsFlag});
}
