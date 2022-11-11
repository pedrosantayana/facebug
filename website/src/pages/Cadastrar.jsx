import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/esm/Container';
import Form from 'react-bootstrap/Form';
import { register } from '../api/facebug';
import { Navigate } from 'react-router-dom';
import React, { useState } from 'react';


function Cadastrar() {
  const [name, setName] = useState();
  const [username, setUsername] = useState();
  const [password, setPassword] = useState();
  const [email, setEmail] = useState();

  const fn = (event) => {
    event.preventDefault();
    register(name, username, email, password)
      .then(res => {
        if(res.status === 0) {
          localStorage.setItem("session", JSON.stringify(res.session));
          // redirect to home
          return (
            <Navigate to="/home" />
          )
        } else {
          console.log("Não foi possível cadastrar. Status: "+res.status);
        }
      });
  }


  return (
    <Container fluid class="bg-warning">
      <h1>Cadastrar</h1>
      <Form onSubmit={fn}>
        <Form.Group className="mb-3" controlId="name">
          <Form.Label>Nome</Form.Label>
          <Form.Control onChange={e => setName(e.target.value)} type="text" placeholder="Nome" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="username">
          <Form.Label>Usuário</Form.Label>
          <Form.Control onChange={e => setUsername(e.target.value)} type="text" placeholder="Nome de Usuário" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="email">
          <Form.Label>Email</Form.Label>
          <Form.Control onChange={e => setEmail(e.target.value)} type="email" placeholder="Email" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="password">
          <Form.Label>Senha</Form.Label>
          <Form.Control onChange={e => setPassword(e.target.value)} type="password" placeholder="Senha" />
        </Form.Group>
        <Button variant="primary" type="submit">
          Cadastrar
        </Button>
      </Form>
    </Container>
  );
}

export default Cadastrar;

