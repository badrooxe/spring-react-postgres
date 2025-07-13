import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

function UpdateStudent() {
  const { id } = useParams();
  const [student, setStudent] = useState({ id: '', firstName: '', lastName: '', email: '', dob: '' });
  const navigate = useNavigate();

  useEffect(() => {
    fetch(`http://spring-react-backend-latest.onrender.com/student/${id}`)
      .then(res => res.json())
      .then(data => {
        console.log("student", data);
        setStudent(data);
        })
      .catch(err => console.error('Fetch student failed:', err));
  }, [id]);

  const handleChange = (e) => setStudent({ ...student, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch(`http://spring-react-backend-latest.onrender.com/student/update/${id}?firstName=${encodeURIComponent(student.firstName)}&lastName=${encodeURIComponent(student.lastName)}&email=${encodeURIComponent(student.email)}&dob=${encodeURIComponent(student.dob)}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' }, 
      body: JSON.stringify(student)  // can also be '{}' or null if your backend ignores it
  })
        .then(data => console.log("student", data))
      .then(() => navigate('/'))
      .catch(err => console.error('Update failed:', err));
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>Update Student</h1>
      <form onSubmit={handleSubmit} style={{ maxWidth: '400px' }}>
        <label>Id:<input type="text" name="id" value={student.id} readOnly /></label>
        <label>First Name:<input type="text" name="firstName" value={student.firstName} onChange={handleChange} required /></label>
        <label>Last Name:<input type="text" name="lastName" value={student.lastName} onChange={handleChange} required /></label>
        <label>Email:<input type="email" name="email" value={student.email} onChange={handleChange} required /></label>
        <label>Date of Birth:<input type="date" name="dob" value={student.dob} onChange={handleChange} required /></label>
        <button type="submit">Update Student</button>
      </form>
    </div>
  );
}

export default UpdateStudent;
