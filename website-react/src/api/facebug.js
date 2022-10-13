const url = "http://localhost:6769";

async function postData(_url, data) {
   // Default options are marked with *
  const response = await fetch(_url, {
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
  const res = await fetch(_url);
  return res.json();
}

export function login(username, password) {
  const res = postData(url+"/usuario/login", {
    username: username,
    password: password
  })
  return res;
}

export function logout(session) {
  const res = postData(url+"/usuario/logout", session);
  return res;
}

export function getUser(username) {
  const res = getData("/usuario/info/"+username);
  return res;
}

export function getFeed(username) {
  const res = getData("/usuario/feed/"+username);
  return res;
}

export function getLikedPosts(username) {
  const res = getData("/postagem/list/"+username);
  return res;
}

export function getPost(id) {
  return getData("/postagem/"+id);
}