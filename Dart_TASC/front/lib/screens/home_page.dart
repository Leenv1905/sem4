import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter_rating_bar/flutter_rating_bar.dart';
import '../models/place.dart';
import '../services/place_service.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final PlaceService _placeService = PlaceService();
  List<Place> places = [];
  bool isLoading = true;

  @override
  void initState() {
    super.initState();
    _loadPlaces();
  }

  Future<void> _loadPlaces() async {
    setState(() => isLoading = true);

    try {
      final result = await _placeService.getAllPlaces();
      setState(() {
        places = result;
        isLoading = false;
      });
    } catch (e) {
      setState(() => isLoading = false);
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(content: Text('Không thể tải dữ liệu')),
        );
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // Header
            Container(
              width: double.infinity,
              padding: const EdgeInsets.fromLTRB(24, 60, 24, 50),
              decoration: const BoxDecoration(
                gradient: LinearGradient(
                  begin: Alignment.topCenter,
                  end: Alignment.bottomCenter,
                  colors: [
                    Color(0xFF7B61FF),   // Tím đậm
                    Color(0xFF9B82FF),   // Tím nhạt
                  ],
                ),
                borderRadius: BorderRadius.only(
                  bottomLeft: Radius.circular(50),
                ),
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: const [
                  Text(
                    "Hi Guy!",
                    style: TextStyle(
                      fontSize: 34,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                      height: 1.1,
                    ),
                  ),
                  SizedBox(height: 8),
                  Text(
                    "Where are you going next?",
                    style: TextStyle(
                      fontSize: 16,
                      color: Colors.white70,
                      fontWeight: FontWeight.w500,
                    ),
                  ),
                ],
              ),
            ),

            const SizedBox(height: 20),

            // Search Bar
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 20),
              child: TextField(
                decoration: InputDecoration(
                  hintText: "Search your destination",
                  prefixIcon: const Icon(Icons.search),
                  filled: true,
                  fillColor: Colors.white,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(16),
                    borderSide: BorderSide.none,
                  ),
                  contentPadding: const EdgeInsets.symmetric(vertical: 18),
                ),
              ),
            ),

            const SizedBox(height: 24),

            // Categories
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 20),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  _buildCategory(Icons.hotel, "Hotels", Colors.orange),
                  _buildCategory(Icons.flight, "Flights", Colors.red),
                  _buildCategory(Icons.rocket, "All", Colors.cyan),
                ],
              ),
            ),

            const SizedBox(height: 30),

            // Popular Destinations
            const Padding(
              padding: EdgeInsets.symmetric(horizontal: 20),
              child: Text(
                "POPULAR DESTINATIONS",
                style: TextStyle(
                  fontSize: 16,
                  fontWeight: FontWeight.bold,
                  letterSpacing: 1.2,
                ),
              ),
            ),

            const SizedBox(height: 16),

            isLoading
                ? const Center(child: CircularProgressIndicator())
                : GridView.builder(
              shrinkWrap: true,
              physics: const NeverScrollableScrollPhysics(),
              padding: const EdgeInsets.symmetric(horizontal: 20),
              gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2,
                crossAxisSpacing: 16,
                mainAxisSpacing: 16,
                childAspectRatio: 0.85,
              ),
              itemCount: places.length,
              itemBuilder: (context, index) {
                final place = places[index];
                return _buildDestinationCard(place);
              },
            ),

            const SizedBox(height: 100),
          ],
        ),
      ),

      // Bottom Navigation
      bottomNavigationBar: Container(
        padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 12),
        margin: const EdgeInsets.all(16),
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.circular(30),
          boxShadow: [
            BoxShadow(
              color: Colors.black.withOpacity(0.05),
              blurRadius: 20,
              spreadRadius: 2,
            ),
          ],
        ),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            _buildNavItem(Icons.home, "HOME", true),
            _buildNavItem(Icons.search, "", false),
            _buildNavItem(Icons.work, "", false),
            _buildNavItem(Icons.person, "", false),
          ],
        ),
      ),
    );
  }

  Widget _buildCategory(IconData icon, String label, Color color) {
    return Column(
      children: [
        Container(
          width: 70,
          height: 70,
          decoration: BoxDecoration(
            color: color.withOpacity(0.1),
            borderRadius: BorderRadius.circular(16),
          ),
          child: Icon(icon, color: color, size: 32),
        ),
        const SizedBox(height: 8),
        Text(label, style: const TextStyle(fontSize: 13, fontWeight: FontWeight.w600)),
      ],
    );
  }

  Widget _buildDestinationCard(Place place) {
    return Container(
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20),
        boxShadow: [
          BoxShadow(
            color: Colors.black.withOpacity(0.1),
            blurRadius: 10,
            spreadRadius: 2,
          ),
        ],
      ),
      child: ClipRRect(
        borderRadius: BorderRadius.circular(20),
        child: Stack(
          fit: StackFit.expand,
          children: [
            CachedNetworkImage(
              imageUrl: place.imageUrl,
              fit: BoxFit.cover,
              placeholder: (context, url) => Container(color: Colors.grey[300]),
              errorWidget: (context, url, error) => const Icon(Icons.error),
            ),
            const Positioned(
              top: 12,
              right: 12,
              child: CircleAvatar(
                radius: 18,
                backgroundColor: Colors.white70,
                child: Icon(Icons.favorite, color: Colors.red, size: 20),
              ),
            ),
            Positioned(
              bottom: 0,
              left: 0,
              right: 0,
              child: Container(
                padding: const EdgeInsets.all(12),
                decoration: const BoxDecoration(
                  gradient: LinearGradient(
                    colors: [Colors.black54, Colors.transparent],
                    begin: Alignment.bottomCenter,
                    end: Alignment.topCenter,
                  ),
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      place.name,
                      style: const TextStyle(
                        color: Colors.white,
                        fontSize: 16,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    const SizedBox(height: 6),
                    Row(
                      children: [
                        RatingBarIndicator(
                          rating: place.rating,
                          itemBuilder: (context, index) => const Icon(
                            Icons.star,
                            color: Colors.amber,
                          ),
                          itemCount: 5,
                          itemSize: 16,
                        ),
                        const SizedBox(width: 6),
                        Text(
                          place.rating.toString(),
                          style: const TextStyle(color: Colors.white, fontSize: 14),
                        ),
                      ],
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildNavItem(IconData icon, String label, bool isActive) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        Icon(
          icon,
          color: isActive ? const Color(0xFF7B61FF) : Colors.grey,
          size: 26,
        ),
        if (label.isNotEmpty)
          Text(
            label,
            style: TextStyle(
              fontSize: 10,
              fontWeight: FontWeight.bold,
              color: isActive ? const Color(0xFF7B61FF) : Colors.grey,
            ),
          ),
      ],
    );
  }
}