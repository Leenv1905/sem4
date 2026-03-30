class Report {

  final int users;
  final int accounts;
  final int transactions;

  final List<String> errors;
  final List<String> anomalies;

  final Map<String, double> totals;

  Report({
    required this.users,
    required this.accounts,
    required this.transactions,
    required this.errors,
    required this.anomalies,
    required this.totals,
  });

  Map<String, dynamic> toJson() {
    return {
      "users": users,
      "accounts": accounts,
      "transactions": transactions,
      "errors": errors,
      "anomalies": anomalies,
      "totalsPerUser": totals,
    };
  }
}