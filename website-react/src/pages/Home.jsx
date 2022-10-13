import React from "react"
import Post from '../components/Post'
import { Redirect } from 'react-router-dom'

export default () => {
  const session = JSON.parse(localStorage.getItem("session"));
  if (session) {
    
    return (
      <Post category="Categoria" title="Qualquer titulo ai" author="Fabio Freire" description="descricao" />
    )
  } else {
    return (<Redirect to="/login" />);
  }
}
