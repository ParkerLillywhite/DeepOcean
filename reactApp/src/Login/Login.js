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

            const token = response.data.token;
            //todo: store token in the redux store

        } catch (error) {
            console.error("Authentication error: ", error);
        }
    };
    //this is to use authentication with stored jwt token
    // axios.get('/api/protected/resource', {
    //     headers: {
    //         Authorization: `Bearer ${localStorage.getItem('jwtToken')}`
    //     }
    // });

    return (
        <div className="login-component">
            <input type="text" placeholder="Username" onChange={e => setUsername(e.target.value)}/>
            <input type="text" plaveholder="Password" onChange={e => setPassword(e.target.value)}/>
            <button onClick={handleAuthenticate}>Login</button>
        </div>
    )
}

export default Login;