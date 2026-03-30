import 'dart:convert';
import 'dart:io';

class FileService {

  static Future<List<dynamic>> readJsonList(String path) async {

    final file = File(path);

    if (!await file.exists()) {
      throw Exception("File not found: $path");
    }

    final content = await file.readAsString();

    return jsonDecode(content);
  }

  static Future<void> writeJson(
      String path,
      Map<String, dynamic> data
      ) async {

    final file = File(path);

    await file.writeAsString(
        JsonEncoder.withIndent('  ').convert(data));
  }
}