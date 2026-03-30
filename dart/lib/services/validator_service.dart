import '../models/account.dart';
import '../models/transaction.dart';
import '../models/user.dart';

class ValidatorService {

  static List<String> validateAccounts(
      List<Account> accounts,
      Map<String, User> users) {

    List<String> errors = [];

    for (var acc in accounts) {

      if (!users.containsKey(acc.userId)) {
        errors.add("Account ${acc.id} references missing user ${acc.userId}");
      }
    }

    return errors;
  }

  static List<String> validateTransactions(
      List<Transaction> txs,
      Map<String, Account> accounts) {

    List<String> errors = [];

    for (var tx in txs) {

      if (!accounts.containsKey(tx.accountId)) {
        errors.add("Transaction ${tx.id} invalid account");
      }

      if (tx.amount <= 0) {
        errors.add("Transaction ${tx.id} amount must be positive");
      }
    }

    return errors;
  }
}