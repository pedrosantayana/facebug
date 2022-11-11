import React from "react"
import Post from '../components/Post'

export default () => {
  const session = JSON.parse(localStorage.getItem("session"));
  console.log(session);
  return (
    <Post category="Categoria" title="Qualquer titulo ai" author="Fabio Freire" description="descricao" />
  )
}
