package service;

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

    if (sessionService.isAuth(request.params("username"), request.params("token"))) {

      UUID id = UUID.randomUUID();
      String username = request.params("username");
      String media = request.params("media");
      String categoryId = request.params("category");
      String title = request.params("title");
      String content = request.params("content");

      Postagem post = new Postagem();

      post.setId(id);
      post.setUsername(username);
      post.setMedia(media);
      post.setCategory(categoryId);
      post.setTitle(title);
      post.setContent(content);
      post.setDate(Date.from(Instant.now()));

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

    if (sessionService.isAuth(request.params("username"), request.params("token"))) {
      Postagem post = postagemDAO.get(request.params("id"));

      if (post != null && post.getUsername() == request.params("username")) {
        String media = request.params("media");
        String categoryId = request.params("category");
        String title = request.params("title");
        String content = request.params("content");

        post.setMedia(media);
        post.setCategory(categoryId);
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

    if (sessionService.isAuth(request.params("username"), request.params("token"))) {

      Postagem post = postagemDAO.get(request.params("id"));

      if (post != null && post.getUsername() == request.params("username")) {
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
    String id = request.queryParams("id");
    Postagem post = postagemDAO.get(id);

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
