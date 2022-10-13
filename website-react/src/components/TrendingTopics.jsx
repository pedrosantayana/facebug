import React from 'react';

function TrendingTopics(props){
    return(
        <main className='bg-warning'>
            <h1 className='py-3 text-dark text-center'>Assuntos do Momento</h1>
            <div className='container-fluid bg-warning'>
                <div className='row justify-content-center'>
                    <div className='col-sm-12 col-md-10 col-lg-8 p-3 p-md-3 p-lg-4 mx-lg-5'>
                        <img src='../img/fortnite.jpeg' className='card-img-top rounded' alt='...'></img>
                        <div className='card-body'>
                            <h5 className='card-title'>{props.titleTrending}</h5>
                            <p className='card-text'>{props.descriptionTrending}</p>
                        </div>
                        <ul className='list-group list-group-flush'>
                            <a href='#' className='text-decoration-none'>
                                <li className='list-group-item bg-warning'>RUST veio para dominar o mundo!</li>
                            </a>
                            <a href='#' className='text-decoration-none'>
                                <li className='list-group-item bg-warning'>PHP é ruim?</li>
                            </a>
                            <a href='#' className='text-decoration-none'>
                                <li className='list-group-item bg-warning'>O JavaScript serve para back-end?</li>
                            </a>
                            <a href='#' className='text-decoration-none'>
                                <li className='list-group-item bg-warning'>Java está morrendo?</li>
                            </a>
                            <a href='#' className='text-decoration-none'>
                                <li className='list-group-item bg-warning'>Robôs</li>
                            </a>
                            <a href='#' className='text-decoration-none'>
                                <li className='list-group-item bg-warning'>Inteligência Artificial</li>
                            </a>
                            <a href='#' className='text-decoration-none'>
                                <li className='list-group-item bg-warning'>Front-End</li>
                            </a>
                        </ul>
                        <div className='card-body'>
                            <a href='https://pt.stackoverflow.com/' className='card-link text-decoration-none'>Stack? Over...Flow!</a>
                            <a href='https://github.com/' className='card-link text-decoration-none'>Explore o Github!</a>
                        </div>
                    </div>
                </div>
            </div>
        </main> 
    )
}

export default TrendingTopics;