package app;

import static spark.Spark.*;

import service.CategoriaService;
import service.PostagemService;
import service.UsuarioService;

public class Facebug {
  private static String semVer = "v0.0.2";

  private static UsuarioService usuarioService = new UsuarioService();
  private static PostagemService postagemService = new PostagemService();
  private static CategoriaService categoriaService = new CategoriaService();

  public static void main(String[] args) {
    port(8080);
    
    // if(System.getenv("PRODUCTION") != null) {
      //   port(80);
      // } else {
        // }
          
    staticFiles.location("/public");
    
    before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    before((request, response) -> System.out.println(request.uri()));

    get("/", (request, response) -> "Facebug API ".concat(semVer));

    post("/usuario/cadastrar", (request, response) -> usuarioService.register(request, response));
    post("/usuario/login", (request, response) -> usuarioService.login(request, response));
    post("/usuario/update", (request, response) -> usuarioService.update(request, response));
    post("/usuario/delete", (request, response) -> usuarioService.delete(request, response));
    get("/usuario/info/:username", (request, response) -> usuarioService.get(request, response));
    get("/usuario/pesquisar/:query", (request, response) -> usuarioService.search(request, response));

    // Gera o feed com postagens relevantes ao usuario
    get("/usuario/feed/:username", (request, response) -> usuarioService.feed(request, response));

    post("/postagem/criar", (request, response) -> postagemService.insert(request, response));
    post("/postagem/update/:id", (request, response) -> postagemService.update(request, response));
    post("/postagem/delete/:id", (request, response) -> postagemService.delete(request, response));
    get("/postagem/:id", (request, response) -> postagemService.get(request, response));
    get("/postagem/listar/categoria/:id", (request, response) -> postagemService.list(request, response));
    get("/postagem/usuario/:username", (request, response) -> postagemService.list(request, response));

    get("/categoria/listar", (request, response) -> categoriaService.list(request, response));

    // get("/feed/:username", (request, response) -> );

  }
}
