import React from 'react';
import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';

var styles = {
  style1: {
      border: 'border: 1px solid lightgray'
  }
}

function Post(props) {
  return (
    <div className='row justify-content-center'>
      <div className='col-sm-12 col-md-10 col-lg-8 p-3 p-md-3 p-lg-4 mx-lg-5'>
        <Card>
          <div className='card-body'>
            <h4 className='card-title text-primary text-center'>{props.category}</h4>
            <h5 className='card-title'>{props.title}</h5>
            <a href='#' className='text-decoration-none'>
              <h6 className='text-warning'>{props.author}</h6>
            </a>
            <button>SEGUIR</button>
            <p className='card-text'>{props.description}</p>
          </div>
          <img className='card-img-top p-2' src='img/img1.jpeg' alt='Card image cap'></img>
          <a href="#" className='text-center p-1 text-danger' style={styles.style1}>
            <i className='fa-solid fa-heart'></i>
          </a>
          <a href='#' className='text-center p-1 text-success' style={styles.style1}>
            <i className='fa-solid fa-comments'></i>
          </a>
        </Card>
      </div>
    </div>
  );
}

export default Post;