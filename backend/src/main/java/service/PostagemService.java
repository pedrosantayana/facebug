package service;

import org.json.JSONObject;

import dao.PostagemDAO;
import model.Categoria;
import model.Postagem;
import spark.Request;
import spark.Response;

public class PostagemService {
  private PostagemDAO postagemDAO = new PostagemDAO();

  public PostagemService() {

  }

  public Object insert(Request request, Response response) {

    UUID newUUID = request.params("newUUID");
    Usuario newUser = request.params("newUser");
    String newMedia = request.params("newMedia");
    Categoria newCategoria = request.params("newCategoria");
    String newTitulo = request.params("newTitulo");
    String newConteudo = request.params("newConteudo");
    Date newDate = request.params("newDate");

    Postagem newPost = new Postagem();

    newPost.setId(newUUID);
    newPost.setUser(newUser);
    newPost.setMedia(newMedia);
    newPost.setCategory(newCategoria);
    newPost.setTitle(newTitulo);
    newPost.setContent(newConteudo);
    newPost.setDate(newDate);

    postagemDAO.insert(newPost);
  }

  public Object update(Request request, Response response) {

    String newMedia = request.params("newMedia");
    Categoria newCategoria = request.params("newCategoria");
    String newTitulo = request.params("newTitulo");
    String newConteudo = request.params("newConteudo");

    Postagem newPost = new Postagem();

    newPost.setMedia(newMedia);
    newPost.setCategory(newCategoria);
    newPost.setTitle(newTitulo);
    newPost.setContent(newConteudo);

    postagemDAO.update(newPost);

    return null;
  }

  public Object delete(Request request, Response response) {
    String id = request.params("id");

    JSONObject resp = new JSONObject();

    postagemDAO.delete(Integer.parseInt(id));

    resp.put("status", 0);

    return resp;
  }

  public Object get(Request request, Response response) {
    response.type("application/json");

    String id = request.queryParams("id");
    Postagem post = postagemDAO.get(Integer.parseInt(id));

    JSONObject resp = new JSONObject();

    resp.put("username", post.getUser());
    resp.put("categoria", post.getCategory());
    resp.put("titulo", post.getTitle());
    resp.put("conteudo", post.getContent());

    return resp;
  }

  public Object list(Request request, Response response) {
    String id = request.queryParams("id");
    Postagem post = postagemDAO.get(Integer.parseInt(id));
    Postagem[] postagens = postagemDAO.list(request.queryParams("username"));

    JSONObject resp = new JSONObject();

    for(Postagem postagem : postagens){
      resp.append("postagens", postagem.toJSON());
    }
    
    return resp;
  }


}
