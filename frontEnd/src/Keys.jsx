
import'./css/Keys.css'
import { rowOne, rowTwo, rowThree } from './keysarray/row';
import Key from './Key.jsx'

export default  function Keys({setStr}){

     return (
        <div className="Keys">
           <div className="rowone row" >
            {rowOne.map((key,idx)=>{
                return (<Key value={key} key={idx} setStr={setStr}></Key>)
            })}
            <Key className='backspace' value='Backspace' setStr={setStr}/>
           </div>


           <div className="rowtwo row">
              {rowTwo.map((key,idx)=>{
                return (<Key value={key} key={idx} setStr={setStr}></Key>)
            })}
           </div>

           <div className="rowthree row">
              {rowThree.map((key,idx)=>{
                return (<Key value={key} key={idx} setStr={setStr}></Key>)
            })}
           </div>
           <Key value='Space' className ='spacebar'setStr={setStr}/>
        </div>
     )

}