import React from "react";
import axios from 'axios';

const Login = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleAuthenticate = async () => {
        try {
            const response = await axios.post('/api/v1/auth/authentication', {
                username,
                password,
            });
        } catch (error) {
            console.error("Authentication error: ", error);
        }
    };

    return (
        <div className="login-component">
            <input type="text" placeholder="Username" onChange={e => setUsername(e.target.value)}/>
            <input type="text" plaveholder="Password" onChange={e => setPassword(e.target.value)}/>
            <button onClick={handleAuthenticate}>Login</button>
        </div>
    )
}

export default Login;