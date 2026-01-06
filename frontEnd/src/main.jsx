import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './css/index.css'
import App from './App.jsx'
import { Auth } from './Auth.jsx'

createRoot(document.getElementById('root')).render(<Auth/>)
