import logo from './logo.svg';
import './App.css';
import Button from 'react-bootstrap/Button';
import React from 'react';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
          <Button href='https://doc.rust-lang.org/book/ch03-03-how-functions-work.html'>Learn Rust</Button>
      </header>
    </div>
  );
}

export default App;
