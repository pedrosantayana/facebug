package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.JSONObject;

import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;

public class UsuarioService {
  private UsuarioDAO usuarioDAO = new UsuarioDAO();
  private SessionService sessionService = new SessionService();
  private MessageDigest md;

  public UsuarioService() {
    try {
      md = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e) {
      System.err.println("Algoritmo SHA-256 não encontrado na JVM.");
      e.printStackTrace();
    }
  }

  public Object register(Request request, Response response) {
    response.type("application/json");
    JSONObject resp = new JSONObject();

    JSONObject body = new JSONObject(request.body());

    String username = body.getString("username");
    String email = body.getString("email");
    byte[] hashedPassword = md.digest(body.getString("password").getBytes());

    Usuario user = new Usuario(username, email, hashedPassword);

    if (usuarioDAO.create(user)) {
      Session session = sessionService.login(user);

      resp.put("status", 0);
      resp.put("session", session.toJSON());
    } else {
      resp.put("status", 1);
    }

    return resp;
  }

  public Object login(Request request, Response response) {
    response.type("application/json");
    JSONObject resp = new JSONObject();
    JSONObject body = new JSONObject(request.body());

    Usuario user = usuarioDAO.get(body.getString("username"));

    if (user != null) {
      byte[] password = md.digest(request.params("password").getBytes());

      if (password.equals(user.getHashedPassword())) {
        Session session = sessionService.login(user);

        resp.put("status", 0);
        resp.put("session", session.toJSON());
      } else {
        resp.put("status", 1);
      }
    }

    return resp;
  }

  public Object update(Request request, Response response) {
    response.type("application/json");
    JSONObject resp = new JSONObject();

    JSONObject body = new JSONObject(request.body());

    Session session = new Session(body.getJSONObject("session"));

    if (sessionService.isAuth(session)) {
      String newUsername = body.getString("newUsername");
      String newEmail = body.getString("newEmail");
      String newPassword = body.getString("newPassword");

      Usuario newUser = new Usuario();
      newUser.setUsername(newUsername);
      newUser.setEmail(newEmail);
      newUser.setHashedPassword(md.digest(newPassword.getBytes()));

      if (usuarioDAO.update(session.getUsername(), newUser)) {
        resp.put("status", 0);
      } else {
        resp.put("status", 1);
      }
    } else {
      resp.put("status", 2);
    }

    return resp;
  }

  public Object delete(Request request, Response response) {
    response.type("application/json");
    JSONObject resp = new JSONObject();

    JSONObject body = new JSONObject(request.body());

    Session session = new Session(body.getJSONObject("session"));

    if (sessionService.isAuth(session)) {
      sessionService.logout(session);

      if (usuarioDAO.delete(session.getUsername())) {
        resp.put("status", 0);
      } else {
        resp.put("status", 1);
      }

    } else {
      resp.put("status", 2);
    }

    return resp;
  }

  public Object get(Request request, Response response) {
    response.type("application/json");
    JSONObject resp = new JSONObject();

    String username = request.params("username");
    Usuario user = usuarioDAO.get(username);

    if (user != null) {
      resp.put("status", 0);
      resp.put("user", user.toJSON());
    } else {
      resp.put("status", 1);
    }

    return resp;
  }

  public Object search(Request request, Response response) {
    response.type("application/json");
    JSONObject resp = new JSONObject();

    Usuario[] users = usuarioDAO.search(request.queryParams("query"));

    for (Usuario user : users) {
      resp.append("users", user.toJSON());
    }

    return resp;
  }

  public Object feed(Request request, Response response) {
    // TODO: feed customizado a partir das curtidas
    return null;
  }

}
