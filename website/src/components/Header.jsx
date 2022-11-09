import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import React from 'react';
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/esm/Button';

function Header() {
  return (
    <Navbar bg="warning" expand="lg">
      <Container>
        <Navbar.Brand href="/">
          <img
            src="/logo.png"
            width="30"
            height="30"
            className="d-inline-block align-top"
            alt="Facebug logo"
          />
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="/home">Home</Nav.Link>
            <Nav.Link href="/trends">Assuntos do Momento</Nav.Link>
            <Nav.Link href="/problems">Problemas, Ajude!</Nav.Link>
            <Nav.Link href="/likes">Curtidas</Nav.Link>
            <Nav.Link href="/newpost">Publicar Post</Nav.Link>
            <Nav.Link href="/profile">Perfil</Nav.Link>
            <Nav.Link href="/cadastrar">Cadastrar</Nav.Link>
            <NavDropdown title="Dropdown" id="basic-nav-dropdown">
              <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.2">
                Another action
              </NavDropdown.Item>
              <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="#action/3.4">
                Separated link
              </NavDropdown.Item>
            </NavDropdown>
          </Nav>
          <Form className="d-flex">
            <Form.Control
              type="search"
              placeholder="Search"
              className="me-2"
              aria-label="Search"
            />
            <Button variant="outline-success">Search</Button>
          </Form>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Header;