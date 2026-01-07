
import './css/App.css'
import Disp from './Disp'
import Keys from './Keys'
import Student from './Student';
import { useEffect, useState } from 'react';
import Leaderborad from './Leaderboard.jsx'
import axios from 'axios';
export default function App() {
    


      let [isCompleted,setIsCompleted] = useState(false);
      let[Timer,setTimer] = useState(10)
      let [str,setStr] = useState('');

      let[Scenario,setScenario] = useState('');
      let[question,setQuestion] = useState('');

        const getdata = async () => {
            try{
                  const response = await axios.get('http://localhost:8080/question');
                  setScenario(response.data.scenario);
                  setQuestion(response.data.question);
                  
            }catch(error){
                  console.error('Error fetching data:', error);
            }
      }
      useEffect(()=>{
            getdata();
      },[])

      function restart(){
            setStr('');
            setIsCompleted(false);
            setTimer(10);
            getdata();
      }

      useEffect(()=>{
            let TimeOut
          if(Timer===0){
            setIsCompleted(true);
            setStr('');

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
                 <p className='qtext'>{Scenario}</p>
                 <h3>{question}</h3>

                  
                  <div className="button" onClick={restart}>Once again lets do it</div>
            </div>
         
            
            <div className="type">
             {isCompleted ? <div className="result"></div>: <Disp str={str}/>}
             <Keys  setStr={setStr}/>
            </div>


            <div className="user">
                  <Student profile='A' name='Arunmuthu' score='100'/>
                  <Leaderborad></Leaderborad>
            </div>
      </div>
}


