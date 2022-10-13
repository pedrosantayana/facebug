import './App.css';
import React from 'react';
import Header from './components/Header';
import Main from './Main';
import Footer from './components/Footer'
import Home from './pages/Home'


function App() {
  return (
    <div className="App"> 
      <Header />
      <Main />
      <Footer />
    </div>
  );
}

export default App;
