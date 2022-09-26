import React from 'react';
import { Routes, Route, BrowserRouter } from 'react-router-dom';

import Home from './pages/Home';
import Login from './pages/Login';

const Main = () => {
  return (
<BrowserRouter>
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
    </Routes>
  </BrowserRouter>
  );
}

export default Main;