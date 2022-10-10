package service;

import java.security.SecureRandom;
import java.util.*;

import model.Usuario;

public class SessionService {
  private HashMap<String, Session> sessionPool = new HashMap<>();

  public SessionService() {

  }

  public boolean isOnline(String username) {

    return sessionPool.containsKey(username);
  }

  public Session login(Usuario user) {
    Session session = new Session(user.getUsername());
    sessionPool.put(user.getUsername(), session);
    return session;
  }

  public void logout(Session session) {
    logout(session.getUsername(), session.getToken());
  }

  public void logout(String username, String token) {
    sessionPool.remove(username);
  }

  public boolean isAuth(String username, String token) {
    return false;
  }
}

class Session {

  private String token;
  private String username;
  private static final SecureRandom secureRandom = new SecureRandom(); // threadsafe
  private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); // threadsafe

  public Session(String username) {
    byte[] randomBytes = new byte[24];
    secureRandom.nextBytes(randomBytes);
    token = base64Encoder.encodeToString(randomBytes);
  }

  public Session(String username, String token) {
    this.token = token;
    this.username = token;
  }

  public String getToken() {
    return token;
  }

  public String getUsername() {
    return username;
  }
}