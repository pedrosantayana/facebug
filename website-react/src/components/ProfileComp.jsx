import React from "react"
import Figure from 'react-bootstrap/Figure'

function ProfileComp(props) {
    return (
        <Figure>
            <Figure.Image
                width={342}
                height={360}
                alt="342x360"
                src="img/perfil.png"
            />
            <Figure.Caption>
                Username: {props.username}
            </Figure.Caption>
            <Figure.Caption>
                Email: {props.email}
            </Figure.Caption>
            <Figure.Caption>
                Senha: {props.password}
            </Figure.Caption>
            <Figure.Caption>
                Seguidores: {props.followers}
            </Figure.Caption>
        </Figure>
    );
}

export default ProfileComp;