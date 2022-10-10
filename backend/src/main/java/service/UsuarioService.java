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
      e.printStackTrace();
    }
  }

  public Object register(Request request, Response response) {
    Usuario user = new Usuario();

    user.setUsername(request.params("username"));
    user.setEmail(request.params("email"));
    user.setHashedPassword(md.digest(request.params("password").getBytes()));

    JSONObject resp = new JSONObject();

    if (usuarioDAO.create(user)) {
      Session session = sessionService.login(user);

      resp.put("status", 0);
      resp.put("username", user.getUsername());
      resp.put("token", session.getToken());
    } else {
      resp.put("status", 1);
    }

    return resp;
  }

  public Object login(Request request, Response response) {
    Usuario user = usuarioDAO.get(request.params("username"));

    JSONObject resp = new JSONObject();

    if (user != null) {
      byte[] password = md.digest(request.params("password").getBytes());

      if (password.equals(user.getHashedPassword())) {
        Session session = sessionService.login(user);

        resp.put("status", 0);
        resp.put("username", user.getUsername());
        resp.put("token", session.getToken());
      } else {
        resp.put("status", 1);
      }
    }

    return resp;
  }

  public Object update(Request request, Response response) {
    String username = request.params("username");
    String token = request.params("token");

    if (sessionService.isAuth(username, token)) {
      String newUsername = request.params("newUsername");
      String newEmail = request.params("newEmail");
      String newPassword = request.params("newPassword");

      Usuario newUser = new Usuario();
      newUser.setUsername(newUsername);
      newUser.setEmail(newEmail);
      newUser.setHashedPassword(md.digest(newPassword.getBytes()));
    } else {

    }

    return null;
  }

  public Object delete(Request request, Response response) {
    String token = request.params("token");
    String username = request.params("username");

    JSONObject resp = new JSONObject();

    if (sessionService.isAuth(username, token)) {
      sessionService.logout(username, token);

      usuarioDAO.delete(username);

      resp.put("status", 0);
    } else {
      resp.put("status", 0);
    }

    return resp;
  }

  public Object get(Request request, Response response) {
    response.type("application/json");

    String username = request.queryParams("username");
    Usuario user = usuarioDAO.get(username);

    JSONObject resp = new JSONObject();

    resp.put("username", user.getUsername());
    resp.put("followers", user.getFollowers());
    resp.put("following", user.getFollowing());
    resp.put("likes", user.getLikes());

    return resp;
  }

  public Object search(Request request, Response response) {
    Usuario[] users = usuarioDAO.search(request.queryParams("username"));

    JSONObject resp = new JSONObject();

    for(Usuario user : users) {
      resp.append("users", user.toJSON());
    }

    return resp;
  }

  public Object feed(Request request, Response response) {
    // TODO: feed customizado a partir das curtidas
    return null;
  }

}
