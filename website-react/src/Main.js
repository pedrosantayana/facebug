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
  const isLoggedin = localStorage.getItem("session");

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={isLoggedin ? <Home /> : <Login />} />
        <Route path="/login" element={isLoggedin ? <Home /> : <Login />} />
        <Route path="/trends" element={isLoggedin ? <Trends /> : <Login />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/newPost" element={isLoggedin ? <NewPost /> : <Login />} />
        <Route path="/problems" element={isLoggedin ? <Problems /> : <Login />} />
        <Route path="/likes" element={<Likes />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Main;