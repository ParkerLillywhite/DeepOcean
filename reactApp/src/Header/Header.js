import React from "react";
import { useNavigate, useLocation} from 'react-router-dom';

const Header = () => {
    const navigate = useNavigate();
    const location = useLocation();

    return (
        <NavBar>
            {!location.pathname !== '/' && (
                <div className="nav-home" onClick={() => navigate('/')}>
                    Home
                </div>
            )}
            {!location.pathname.includes('visualizer') && (
                <div className="visualizer" onClick={() => navigate('microphone-visualizer')}>
                    Microphone
                </div>
            )}

        </NavBar>
    )
}

const NavBar = (props) => {
    return (
        <div className="navbar">
            <ul className="navbar-nav"> {props.children} </ul>
        </div>
    )
}

export default Header;
