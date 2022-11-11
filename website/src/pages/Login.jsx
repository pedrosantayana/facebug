import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/esm/Container';
import Form from 'react-bootstrap/Form';
import { login } from '../api/facebug';
import React, { useState } from 'react';
import {Navigate} from 'react-router-dom';

function Login() {
  const [username, setUsername] = useState();
  const [password, setPassword] = useState();

  const fn = (event) => {
    console.log("[login]");
    event.preventDefault();
    login(username, password)
      .then(res => {
        if(res.status === 0) {
          localStorage.setItem("session", JSON.stringify(res.session));
          console.log("logou");
          // redirect to home
          return (
            <Navigate to="/home" />
          )
        } else {
          console.log("Não foi possível logar. Status: "+res.status);
        }
      });
  }


  return (
    <Container fluid class="bg-warning">
      <h1>Login</h1>
      <Form onSubmit={fn}>
        <Form.Group className="mb-3" controlId="username">
          <Form.Label>Usuário</Form.Label>
          <Form.Control onChange={e => setUsername(e.target.value)} type="text" placeholder="Nome de Usuário" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="password">
          <Form.Label>Senha</Form.Label>
          <Form.Control onChange={e => setPassword(e.target.password)} type="password" placeholder="Senha" />
        </Form.Group>
        <Button variant="primary" type="submit">
          Login
        </Button>
      </Form>
    </Container>
  );
}

export default Login;