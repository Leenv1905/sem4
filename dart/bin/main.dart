import 'package:args/args.dart';

import '../lib/models/user.dart';
import '../lib/models/account.dart';
import '../lib/models/transaction.dart';

import '../lib/services/file_service.dart';
import '../lib/services/validator_service.dart';
import '../lib/services/anomaly_service.dart';
import '../lib/services/statistics_service.dart';

import '../lib/report/report.dart';

Future<void> main(List<String> arguments) async {

  final parser = ArgParser()
    ..addOption('users', defaultsTo: 'data/users.json')
    ..addOption('accounts', defaultsTo: 'data/accounts.json')
    ..addOption('transactions', defaultsTo: 'data/transactions.json');

  final args = parser.parse(arguments);

  List<String> errors = [];

  try {

    final usersJson =
    await FileService.readJsonList(args['users']);

    final accountsJson =
    await FileService.readJsonList(args['accounts']);

    final txJson =
    await FileService.readJsonList(args['transactions']);

    List<User> users = [];
    List<Account> accounts = [];
    List<Transaction> transactions = [];

    for (var u in usersJson) {
      try {
        users.add(User.fromJson(u));
      } catch (e) {
        errors.add(e.toString());
      }
    }

    for (var a in accountsJson) {
      try {
        accounts.add(Account.fromJson(a));
      } catch (e) {
        errors.add(e.toString());
      }
    }

    for (var t in txJson) {
      try {
        transactions.add(Transaction.fromJson(t));
      } catch (e) {
        errors.add(e.toString());
      }
    }

    Map<String, User> userMap = {
      for (var u in users) u.id: u
    };

    Map<String, Account> accountMap = {
      for (var a in accounts) a.id: a
    };

    errors.addAll(
        ValidatorService.validateAccounts(accounts, userMap));

    errors.addAll(
        ValidatorService.validateTransactions(transactions, accountMap));

    var anomalies =
    AnomalyService.detectLargeTransactions(transactions);

    var totals =
    StatisticsService.totalTransactionPerUser(transactions, accountMap);

    Report report = Report(
      users: users.length,
      accounts: accounts.length,
      transactions: transactions.length,
      errors: errors,
      anomalies: anomalies.map((e) => e.id).toList(),
      totals: totals,
    );

    await FileService.writeJson(
        "output/report.json",
        report.toJson());

    print("Report generated successfully");

  } catch (e) {

    print("Fatal error: $e");

  }
}