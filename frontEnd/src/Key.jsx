import './css/key.css'
import { useEffect, useState } from 'react'

export default function Key(props){
    let [active,setactive] = useState(false);
  useEffect(() => {

    const handleKeyPress = (e) => {

     if (props.value === 'Backspace' && e.key === 'Backspace') {
        setactive(true);
        setTimeout(()=>{
          setactive(false);
        },150)
        props.setStr(prev => prev.slice(0, -1))
        return
      }

      
      if (props.value === 'Space' && e.key === ' ') {
         setactive(true);
        setTimeout(()=>{
          setactive(false);
        },150)
        props.setStr(prev => prev + ' ')
        return
      }


    if(e.key.toUpperCase() == props.value){
       setactive(true);
        setTimeout(()=>{
          setactive(false);
        },150)
      props.setStr(prev => prev+e.key)
    }
    }

    window.addEventListener('keydown', handleKeyPress)

     return (()=>{
        console.log("unmount");
        window.removeEventListener('keydown', handleKeyPress)
     })
  }, [])

    return (<div  className= {`key ${props.className || ''} ${active?'active':''}`} >
        <p>{props.value}
        </p>
    </div>)
}