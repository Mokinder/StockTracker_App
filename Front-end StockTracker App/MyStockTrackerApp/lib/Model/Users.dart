


class Users {
  final int id;
  final String username;
  final String full_name;
  final String email_id;

  Users({
    required this.id,
    required this.username,
    required this.full_name,
    required this.email_id,
  });

  factory Users.fromJson(Map<String, dynamic> json) {
    return Users(
      id: json['id'],
      username: json['username'],
      full_name: json['full_name'],
      email_id: json['email_id'],
    );
  }
}