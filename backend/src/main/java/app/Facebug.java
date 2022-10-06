package app;

import static spark.Spark.*;

import service.PostagemService;
import service.ProdutoService;
import service.UsuarioService;

public class Facebug {
  private static ProdutoService produtoService = new ProdutoService();
  private static UsuarioService usuarioService = new UsuarioService();
  private static PostagemService postagemService = new PostagemService();
  private static FeedService feedService = new FeedService();
  public static void main(String[] args) {
    port(6789);

    staticFiles.location("/public");

    post("/usuario/cadastrar", (request, response) -> UsuarioService.register(request, response));
    post("/usuario/login", (request, response) -> UsuarioService.login(requst, response));
    post("/usuario/update", (request, response) -> UsuarioService.update(request, response));
    post("/usuario/delete", (request, response) -> UsuarioService.delete(request, response));
    get("/usuario/info/:username", (request, response) -> UsuarioService.get(request, response));
    get("/usuario/pesquisar/:username", (request, response) -> UsuarioService.search(request, response));
    get("/usuario/feed/:username", (request, response) -> UsuarioService.feed(request, response));

    post("/postagem/criar", (request, response) -> PostagemService.insert(request, response));
    post("/postagem/update", (request, response) -> PostagemService.update(request, response));
    post("/postagem/delete", (request, response) -> PostagemService.delete(request, response));
    get("/postagem/:id", (request, response) -> PostagemService.get(request, response));
    get("/postagem/listar/categoria/:id", (request, response) -> PostagemService.list(request, response));
    get("/postagem/usuario/:username", (request, response) -> PostagemService.list(request, response));

    
    get("/feed/:username", (request, response) -> );

    get("/categoria/listar", (request, response) -> );

  }
}