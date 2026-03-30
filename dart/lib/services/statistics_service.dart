import '../models/account.dart';
import '../models/transaction.dart';

class StatisticsService {

  static Map<String, double> totalTransactionPerUser(
      List<Transaction> txs,
      Map<String, Account> accounts) {

    Map<String, double> totals = {};

    for (var tx in txs) {

      var acc = accounts[tx.accountId];

      if (acc == null) continue;

      totals.update(
        acc.userId,
            (v) => v + tx.amount,
        ifAbsent: () => tx.amount,
      );
    }

    return totals;
  }
}