package model;

public class Usuario {
  private String username;
  private String email;
  private byte[] hashedPassword;
  private int followers;
  private int likes;
  private int following;

  public Usuario() {
    
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public byte[] getHashedPassword() {
    return hashedPassword;
  }

  public void setHashedPassword(byte[] hashedPassword) {
    this.hashedPassword = hashedPassword;
  }

  public int getFollowers() {
    return followers;
  }

  public void setFollowers(int followers) {
    this.followers = followers;
  }

  public int getLikes() {
    return likes;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  public int getFollowing() {
    return following;
  }

  public void setFollowing(int following) {
    this.following = following;
  }
}
