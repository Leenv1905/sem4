import '../models/account.dart';
import '../models/transaction.dart';

class AnomalyService {

  static List<Transaction> detectLargeTransactions(
      List<Transaction> txs) {

    return txs.where((t) => t.amount > 10000).toList();
  }

  static List<String> detectFrequentTransactions(
      List<Transaction> txs) {

    Map<String, int> count = {};

    for (var tx in txs) {
      count.update(tx.accountId, (v) => v + 1, ifAbsent: () => 1);
    }

    return count.entries
        .where((e) => e.value > 5)
        .map((e) => e.key)
        .toList();
  }

  static Map<String, double> totalByUser(
      List<Transaction> txs,
      Map<String, Account> accounts) {

    Map<String, double> totals = {};

    for (var tx in txs) {

      var account = accounts[tx.accountId];

      if (account == null) continue;

      totals.update(
        account.userId,
            (v) => v + tx.amount,
        ifAbsent: () => tx.amount,
      );
    }

    return totals;
  }
}