import React from 'react';
import { Routes, Route, BrowserRouter } from 'react-router-dom';

import Home from './pages/Home';
import Login from './pages/Login';
import Trends from './pages/Trends';
import Profile from './pages/Profile';


const Main = () => {
  return (
<BrowserRouter>
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/trends" element={<Trends />} />
      <Route path="/profile" element={<Profile />}/>
    </Routes>
  </BrowserRouter>
  );
}

export default Main;