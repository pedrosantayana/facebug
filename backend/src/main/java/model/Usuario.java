package model;

public class Usuario {
  private String username;
  private String email;
  private byte[] hashedPassword;

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
}
