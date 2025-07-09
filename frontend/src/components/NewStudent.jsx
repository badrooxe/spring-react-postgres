import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function NewStudent() {
  const [student, setStudent] = useState({ firstName: '', lastName: '', email: '', dob: '' });
  const navigate = useNavigate();

  const handleChange = (e) => setStudent({ ...student, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch('http://localhost:8080/student/add', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(student)
    })
      .then(() => navigate('/'))
      .catch(err => console.error('Add failed:', err));
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>Add New Student</h1>
      <form onSubmit={handleSubmit} style={{ maxWidth: '400px' }}>
        <label>First Name:<input type="text" name="firstName" value={student.firstName} onChange={handleChange} required /></label>
        <label>Last Name:<input type="text" name="lastName" value={student.lastName} onChange={handleChange} required /></label>
        <label>Email:<input type="email" name="email" value={student.email} onChange={handleChange} required /></label>
        <label>Date of Birth:<input type="date" name="dob" value={student.dob} onChange={handleChange} required /></label>
        <button type="submit">Add Student</button>
      </form>
    </div>
  );
}

export default NewStudent;
