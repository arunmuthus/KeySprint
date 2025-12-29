
import './css/App.css'
import Disp from './Disp'
import Keys from './Keys'
import Student from './Student';
import { useEffect, useState } from 'react';

export default function App() {
      let[Timer,setTimer] = useState(10)
      let [str,setStr] = useState('');
      
      useEffect(()=>{
            let TimeOut
          if(Timer===0){
             setTimer(prev=>prev+10);
          }
          else{
         TimeOut = setTimeout(()=>{
            setTimer(prev=>prev-1);
          },1000)
      
          return()=>{
            clearTimeout(TimeOut);
          }
          
      }
      },[Timer])
      return <div className="App">
            <div className="question">
                  <div className="timer"><h1>{Timer}</h1></div>

                  
                 <h2>Behavioral Scenario Question</h2>
                 <p className='qtext'>You are working on an important project with a tight deadline. Midway through development, you realize that a part of your work has a flaw that could cause issues after release. Fixing it properly may delay delivery, while ignoring it helps meet the deadline. At the same time, your manager is under pressure to deliver on time and does not want any bad news</p>
                 <h3>What do you do, and how do you handle the situation?</h3>
                  
                  
                  <div className="button">Once again lets do it</div>
            </div>

            <div className="type">
            <Disp str={str}/>
            <Keys  setStr={setStr}/>
            </div>


            <div className="user">
                  <Student profile='A' name='Arunmuthu' score='100'/>
            </div>
      </div>
}


