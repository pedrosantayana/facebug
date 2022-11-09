import React from 'react';
import Container from 'react-bootstrap/esm/Container';

var styles = {
    style1: {
        background: 'black',
        borderTop: '2px solid white'
    },
    style2: {
        innerWidth: '100px'
    }
}

function Footer(){
    return(
        <footer>
            <Container fluid>
                <div className="row text-center" style={styles.style1}>
                    <a href="/">
                        <img src="./src/img/logo2.png" alt="logo da empresa" style={styles.style2}/>
                    </a>
                </div>
            </Container>
      </footer>
    );
}

export default Footer;