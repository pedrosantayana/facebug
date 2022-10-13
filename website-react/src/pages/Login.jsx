import React from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/esm/Container';
import Form from 'react-bootstrap/Form';
import { login } from '../api/facebug';
function Login() {
  state = {
    username: null,
    password: null
  }
  const fn = (event) => {
    event.preventDefault();
    login(this.state.username, this.state.password)
      .then(res => {
        if(res.status == 0) {
          localStorage.setItem("session", JSON.stringify(res.session));
          // redirect to home
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
          <Form.Control onChange={e => this.setState({username: e.target.value, password: this.state.password})} type="text" placeholder="Nome de Usuário" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="password">
          <Form.Label>Senha</Form.Label>
          <Form.Control onChange={e => this.setState({username: this.state.username, password: e.target.value})} type="password" placeholder="Senha" />
        </Form.Group>
        <Button variant="primary" type="submit">
          Login
        </Button>
      </Form>
    </Container>
  );
}

export default Login;