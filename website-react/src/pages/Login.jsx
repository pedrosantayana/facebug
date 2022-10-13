import React from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/esm/Container';
import Form from 'react-bootstrap/Form';

function Login() {
  const fn = (event) => {
    event.preventDefault();
    // const username = event.
  }


  return (
    <Container fluid class="bg-warning">
      <h1>Login</h1>
      <Form onSubmit={fn}>
        <Form.Group className="mb-3" controlId="username">
          <Form.Label>Usuário</Form.Label>
          <Form.Control id="username" type="text" placeholder="Nome de Usuário" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="password">
          <Form.Label>Senha</Form.Label>
          <Form.Control id="password" type="password" placeholder="Senha" />
        </Form.Group>
        <Button variant="primary" type="submit">
          Login
        </Button>
      </Form>
    </Container>
  );
}

export default Login;