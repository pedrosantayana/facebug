import React from 'react';

function TrendingTopics(props){
    return(
        <main className='bg-warning'>
            <h1 className='py-3 text-dark text-center'>Assuntos do Momento</h1>
            <div className='container-fluid bg-warning'>
                <div className='row justify-content-center'>
                    <div className='col-sm-12 col-md-10 col-lg-8 p-3 p-md-3 p-lg-4 mx-lg-5'>
                        <img src='../img/fortnite'></img>
                    </div>
                </div>
            </div>
        </main> 
    )
}

export default TrendingTopics;

