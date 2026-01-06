import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './css/index.css'
import App from './App.jsx'
import { Auth } from './Auth.jsx'
import { BrowserRouter , Routes ,Route } from 'react-router-dom'
createRoot(document.getElementById('root')).render(
<BrowserRouter>
<Routes>
    <Route path='/' element={<Auth/>}></Route>
    <Route path='/app' element={<App/>}></Route>
</Routes>
</BrowserRouter>
)
