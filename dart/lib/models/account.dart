class Account {

  final String id;
  final String userId;
  final double balance;

  Account({
    required this.id,
    required this.userId,
    required this.balance,
  });

  factory Account.fromJson(Map<String, dynamic> json) {

    if (json['id'] == null || json['userId'] == null) {
      throw FormatException("Invalid account data");
    }

    return Account(
      id: json['id'],
      userId: json['userId'],
      balance: (json['balance'] ?? 0).toDouble(),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      "id": id,
      "userId": userId,
      "balance": balance,
    };
  }
}