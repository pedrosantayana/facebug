package model;

import java.util.UUID;

import org.json.JSONObject;

public class Usuario {
  private String username;
  private String email;
  private byte[] hashedPassword;
  private UUID[] followers;
  private int likes;
  private UUID[] following;

  public Usuario() {
    
  }

  public JSONObject toJSON() {
    JSONObject resp = new JSONObject();
    resp.put("username", username);
    resp.put("followers", followers);
    resp.put("following", following);
    resp.put("likes", likes);

    return resp;
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

  public UUID[] getFollowers() {
    return followers;
  }

  public void setFollowers(UUID[] followers) {
    this.followers = followers;
  }

  public int getLikes() {
    return likes;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  public UUID[] getFollowing() {
    return following;
  }

  public void setFollowing(UUID[] following) {
    this.following = following;
  }
}
