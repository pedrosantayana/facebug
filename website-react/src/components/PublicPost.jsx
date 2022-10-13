import React from 'react'
import Form from 'react-bootstrap/Form';

var styles = {
    style1: {
        width: '300px',
        margin: '0 auto',
        marginBottom: '10px'
    }, 
    style2: {
        marginRight: '10px'
    }
}

function PublicPost() {
    return (
        <main>
            <h1 className='py-3 text-dark text-center'>Nova Postagem</h1>
            <Form.Select aria-label="Default select example" style={styles.style1}>
                <option>ESCOLHA A CATEGORIA</option>
                <option value="1">PROBLEMA</option>
                <option value="2">SOCIAL</option>
            </Form.Select>
            <div className='container-fluid justify-content-center'>
                <div className='col-12 col-md-10 col-lg-8 mx-auto'>
                    <form action='' className='justify-content-center'>
                        <textarea className='form-control' id='content' placeholder='Digite seu post aqui' rows='10'></textarea>
                        <span>Max: 300 caracteres</span>
                        <div className='text-end'>
                            <button type='submit' className='btn btn-primary' style={styles.style2}>Carregar imagem</button>
                            <button type='button' className='btn btn-success'>Publicar</button>
                        </div>
                    </form>
                </div>
            </div>
        </main>
    )
}

export default PublicPost
