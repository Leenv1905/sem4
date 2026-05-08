class Place {
  final int id;
  final String name;
  final String location;
  final String description;
  final String imageUrl;
  final double rating;
  final int price;

  Place({
    required this.id,
    required this.name,
    required this.location,
    required this.description,
    required this.imageUrl,
    required this.rating,
    required this.price,
  });

  factory Place.fromJson(Map<String, dynamic> json) {
    return Place(
      id: json['id'],
      name: json['name'],
      location: json['location'],
      description: json['description'],
      imageUrl: json['imageUrl'],
      rating: json['rating'].toDouble(),
      price: json['price'],
    );
  }
}