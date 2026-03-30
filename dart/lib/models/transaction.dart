class Transaction {

  final String id;
  final String accountId;
  final double amount;
  final String type;
  final DateTime timestamp;

  Transaction({
    required this.id,
    required this.accountId,
    required this.amount,
    required this.type,
    required this.timestamp,
  });

  factory Transaction.fromJson(Map<String, dynamic> json) {

    if (json['id'] == null || json['accountId'] == null) {
      throw FormatException("Invalid transaction data");
    }

    return Transaction(
      id: json['id'],
      accountId: json['accountId'],
      amount: (json['amount'] ?? 0).toDouble(),
      type: json['type'] ?? "unknown",
      timestamp: DateTime.parse(json['timestamp']),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      "id": id,
      "accountId": accountId,
      "amount": amount,
      "type": type,
      "timestamp": timestamp.toIso8601String(),
    };
  }
}