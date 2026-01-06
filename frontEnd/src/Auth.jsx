import  './css/Auth.css';
import {useState} from 'react';
import {Login} from './login.jsx';
import {Signup} from './signup.jsx';
export const Auth = () => {

    let [showLogin,setShowLogin]=useState(true);
    return(
        <div className="auth-container">

        <div className="logo">
            <div className="a k"></div>
            <div className="a s"></div>
            <div className="a l"></div>
            <h1><h1>Key</h1>
            <h1>Sprint</h1></h1>
            <h2>Lets Type Better Together</h2>     
        </div>

        <div className="auth">
              <div className="form">
                <div className="head">
                    <div className="login" onClick={()=>{setShowLogin(true)}}>Login</div>
                    <div className="signup" onClick={()=>{setShowLogin(false)}}>Signup</div>
                </div>
                <div className="field">
                  {showLogin ? <Login/> : <Signup/>}
                </div>
              </div>
        </div>
        </div>
    )
}