import './css/Student.css'
const Student = (props)=>(
  <div className="student" > 
     <div className="profile"><p className="profiletter">{props.profile}</p></div>
     <h3 className="name">{props.name}</h3>
     <p className='scoretext'>Your Highest Score</p>
     <p className="score">{props.score}</p>
  </div>
)

export default Student;