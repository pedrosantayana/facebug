const url = "http://localhost:8080";

// post("/usuario/cadastrar", (request, response) -> usuarioService.register(request, response));
// post("/usuario/login", (request, response) -> usuarioService.login(request, response));
// post("/usuario/update", (request, response) -> usuarioService.update(request, response));
// post("/usuario/delete", (request, response) -> usuarioService.delete(request, response));
// get("/usuario/info/:username", (request, response) -> usuarioService.get(request, response));
// get("/usuario/pesquisar/:query", (request, response) -> usuarioService.search(request, response));

// // Gera o feed com postagens relevantes ao usuario
// get("/usuario/feed/:username", (request, response) -> usuarioService.feed(request, response));

// post("/postagem/criar", (request, response) -> postagemService.insert(request, response));
// post("/postagem/update/:id", (request, response) -> postagemService.update(request, response));
// post("/postagem/delete/:id", (request, response) -> postagemService.delete(request, response));
// get("/postagem/:id", (request, response) -> postagemService.get(request, response));
// get("/postagem/listar/categoria/:id", (request, response) -> postagemService.list(request, response));
// get("/postagem/usuario/:username", (request, response) -> postagemService.list(request, response));

// get("/categoria/listar", (request, response) -> categoriaService.list(request, response));

// get("/feed/:username", (request, response) -> );



async function postData(_url, data) {
   // Default options are marked with *
  const response = await fetch(url+_url, {
    method: 'POST', // *GET, POST, PUT, DELETE, etc.
    mode: 'cors', // no-cors, *cors, same-origin
    cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
    credentials: 'same-origin', // include, *same-origin, omit
    headers: {
      'Content-Type': 'application/json'
      // 'Content-Type': 'application/x-www-form-urlencoded',
    },
    redirect: 'follow', // manual, *follow, error
    referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
    body: JSON.stringify(data) // body data type must match "Content-Type" header
  });
  return response.json(); // parses JSON response into native JavaScript objects
}

async function getData(_url) {
  const res = await fetch(url+_url);
  return res.json();
}

export function register(username, email, password) {
  return postData("/usuario/cadastrar", {
    username: username,
    email: email,
    password: password
  })
}

export function login(username, password) {
  const res = postData("/usuario/login", {
    username: username,
    password: password
  })
  return res;
}

export function logout(session) {
  const res = postData("/usuario/logout", session);
  return res;
}

export function getUser(username) {
  const res = getData("/usuario/info/"+username);
  return res;
}

export function generateFeed(username) {
  // const res = getData("/usuario/feed/"+username);
  return getData("/postagem/listar/categoria/");
}

export function getLikedPosts(username) {
  const res = getData("/postagem/list/"+username);
  return res;
}

export function createPost(title, content, midia, categoryId, session) {
  return postData("/postagem/criar", {
    title: title,
    content: content,
    midia: midia,
    categoryId: categoryId,
    session: session
  })
}

export function getPost(id) {
  return getData("/postagem/"+id);
}

export function deletePost(id, session) {
  return postData("/postagem/delete/"+id, session);
}

// export function updatePost(id, title, content, session)

export function getCategories() {
  return getData("/categoria/list");
}