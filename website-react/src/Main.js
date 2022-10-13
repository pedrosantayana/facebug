import React from 'react';
import { Routes, Route, BrowserRouter } from 'react-router-dom';

import Home from './pages/Home';
import Login from './pages/Login';
import Trends from './pages/Trends';
import Profile from './pages/Profile';
import NewPost from './pages/NewPost';
import Problems from './pages/Problems';
import Likes from './pages/Likes';

const Main = () => {
  return (
<BrowserRouter>
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/trends" element={<Trends />} />
      <Route path="/profile" element={<Profile />}/>
      <Route path="/newPost" element={<NewPost />}/>
      <Route path="/problems" element={<Problems />}/>
      <Route path="/likes" element={<Likes />}/>
    </Routes>
  </BrowserRouter>
  );
}

export default Main;