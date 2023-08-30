import React from "react";
import { useHistory } from 'react-router-dom';

const Header = () => {
    let history = useHistory();
    const changeRoute = (path) => {
        history.push(path);
    }

    return (
        <NavBar>
            <div className="nav-home" onClick={() => changeRoute('/')}>
                home
            </div>
            <div className="dummy-one" onClick={() => changeRoute('placeholder1')}>
                placeHolder 1
            </div>
        </NavBar>
    )
}

const NavBar = () => {
    return (
        <div className="navbar">
            <ul className="navbar-nav"> {props.children} </ul>
        </div>
    )
}

export default Header;
