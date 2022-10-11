package service;

import java.security.acl.Owner;
import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

import org.json.JSONObject;

import dao.PostagemDAO;
import model.Categoria;
import model.Postagem;
import model.Usuario;
import spark.Request;
import spark.Response;

public class PostagemService {
  private PostagemDAO postagemDAO = new PostagemDAO();
  private SessionService sessionService = new SessionService();

  public PostagemService() {

  }

  public Object insert(Request request, Response response) {
    response.type("application/json");
    JSONObject resp = new JSONObject();

    JSONObject body = new JSONObject(request.body());

    Session session = new Session(body.getJSONObject("session"));

    if (sessionService.isAuth(session)) {
      String ownerUsername = request.params("ownerUsername");
      String media = request.params("media");
      String categoryId = request.params("categoryId");
      String title = request.params("title");
      String content = request.params("content");

      Postagem post = new Postagem(title, content, media, UUID.fromString(categoryId), ownerUsername);

      if (postagemDAO.insert(post)) {
        resp.put("status", 0);
      } else {
        resp.put("status", 2);
      }
    } else {
      resp.put("status", 1);
    }

    return resp;
  }

  public Object update(Request request, Response response) {
    response.type("application/json");
    JSONObject resp = new JSONObject();

    JSONObject body = new JSONObject(request.body());

    Session session = new Session(body.getJSONObject("session"));

    if (sessionService.isAuth(session)) {
      Postagem post = postagemDAO.get(UUID.fromString(request.params("id")));

      if (post != null && post.getOwnerUsername().equals(session.getUsername())) {
        String media = request.params("media");
        String categoryId = request.params("categoryId");
        String title = request.params("title");
        String content = request.params("content");

        post.setMedia(media);
        post.setCategoryId(UUID.fromString(categoryId));
        post.setTitle(title);
        post.setContent(content);

        if (postagemDAO.update(post)) {
          resp.put("status", 0);
        } else {
          resp.put("status", 1);
        }
      } else {
        resp.put("status", 2);
      }
    } else {
      resp.put("status", 3);
    }

    return resp;
  }

  public Object delete(Request request, Response response) {
    response.type("application/json");
    JSONObject resp = new JSONObject();

    JSONObject body = new JSONObject(request.body());

    Session session = new Session(body.getJSONObject("session"));

    if (sessionService.isAuth(session)) {

      Postagem post = postagemDAO.get(UUID.fromString(request.params("id")));

      if (post != null && post.getOwnerUsername().equals(session.getUsername())) {
        if (postagemDAO.delete(post.getId())) {
          resp.put("status", 0);
        } else {
          resp.put("status", 1);
        }
      } else {
        resp.put("status", 2);
      }
    } else {
      resp.put("status", 3);
    }

    return resp;
  }

  public Object get(Request request, Response response) {
    response.type("application/json");
    String id = request.params("id");
    Postagem post = postagemDAO.get(UUID.fromString(id));

    return post.toJSON();
  }

  public Object list(Request request, Response response) {
    String id = request.queryParams("id");
    Postagem post = postagemDAO.get(Integer.parseInt(id));
    Postagem[] postagens = postagemDAO.list(request.queryParams("username"));

    JSONObject resp = new JSONObject();

    for (Postagem postagem : postagens) {
      resp.append("postagens", postagem.toJSON());
    }

    return resp;
  }
}
