import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/place.dart';

class PlaceService {
  // static const bool useMockData = true;   // ← Đổi thành false khi gọi API thật
  static const bool useMockData = false;   // ← Đổi thành false khi gọi API thật

  // Mock Data (dùng để test UI)
  static final List<Place> mockPlaces = [
    Place(
      id: 1,
      name: "Vịnh Hạ Long",
      location: "Quảng Ninh",
      description: "Di sản thiên nhiên thế giới",
      imageUrl: "https://picsum.photos/id/1015/800/600",
      rating: 4.9,
      price: 2500000,
    ),
    Place(
      id: 2,
      name: "Phố cổ Hội An",
      location: "Quảng Nam",
      description: "Thành phố cổ kính và lãng mạn",
      imageUrl: "https://picsum.photos/id/133/800/600",
      rating: 4.8,
      price: 1200000,
    ),
    Place(
      id: 3,
      name: "Sapa",
      location: "Lào Cai",
      description: "Thị trấn trong mây",
      imageUrl: "https://picsum.photos/id/1018/800/600",
      rating: 4.7,
      price: 1800000,
    ),
    Place(
      id: 4,
      name: "Đà Lạt",
      location: "Lâm Đồng",
      description: "Thành phố ngàn hoa",
      imageUrl: "https://picsum.photos/id/133/800/600",
      rating: 4.8,
      price: 1500000,
    ),
    Place(
      id: 5,
      name: "Phú Quốc",
      location: "Kiên Giang",
      description: "Đảo ngọc thiên đường",
      imageUrl: "https://picsum.photos/id/1016/800/600",
      rating: 4.9,
      price: 3000000,
    ),
  ];

  Future<List<Place>> getAllPlaces() async {
    if (useMockData) {
      // Giả lập delay để giống gọi API thật
      await Future.delayed(const Duration(seconds: 1));
      return mockPlaces;
    }

    // === Code gọi API thật ===
    try {
      final response = await http.get(
        Uri.parse('http://192.168.1.152:8080/api/places'), // ← thay IP thật
      );

      if (response.statusCode == 200) {
        final List<dynamic> data = json.decode(response.body);
        return data.map((json) => Place.fromJson(json)).toList();
      } else {
        throw Exception('Failed to load places');
      }
    } catch (e) {
      print('API Error: $e');
      // Fallback về mock nếu API lỗi
      return mockPlaces;
    }
  }
}