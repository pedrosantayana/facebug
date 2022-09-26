import React from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/esm/Container';
import Form from 'react-bootstrap/Form';

function Login() {
  return (
    <Container fluid class="bg-warning">
      <h1>Login</h1>
      <Form>
        <Form.Group className="mb-3" controlId="username">
          <Form.Label>Usuário</Form.Label>
          <Form.Control type="text" placeholder="Nome de Usuário" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="password">
          <Form.Label>Senha</Form.Label>
          <Form.Control type="password" placeholder="Senha" />
        </Form.Group>
        <Button variant="primary" type="submit">
          Login
        </Button>
      </Form>
    </Container>
  );
}

export default Login;