import './css/Auth.css'
import { useNavigate } from 'react-router-dom'
export const Login = ()=>{
    const navigate = useNavigate();
    return (<div className='inps'>
    <input className="input" type="text" placeholder='Enter Your Username'/>
    <input  className="input" type="password"  placeholder='Enter Your Password'/>
    <div className="btn" onClick={()=>{
        navigate('/app');
    }}>Login</div>
    </div>)}