import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Students() {
  const [students, setStudents] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetch('http://spring-react-backend-latest.onrender.com/student')
      .then(res => res.json())
      .then(data => setStudents(data))
      .catch(err => console.error('Error fetching students:', err));
  }, []);

  const deleteStudent = (id) => {
    if (!window.confirm('Are you sure you want to delete this student?')) return;
    fetch(`http://spring-react-backend-latest.onrender.com/student/delete/${id}`, { method: 'DELETE' })
      .then(() => setStudents(prev => prev.filter(s => s.id !== id)))
      .catch(err => console.error('Delete failed:', err));
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>List of Studentssss</h1>
      <button onClick={() => navigate('/student/add')}>Add Student</button>
      <table style={{ borderCollapse: 'collapse', width: '100%', marginTop: '20px' }}>
        <thead>
          <tr>
            <th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Date of Birth</th><th>Age</th><th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {students.map(s => (
            <tr key={s.id}>
              <td>{s.id}</td>
              <td>{s.firstName}</td>
              <td>{s.lastName}</td>
              <td>{s.email}</td>
              <td>{s.dob}</td>
              <td>{s.age}</td>
              <td>
                <button onClick={() => navigate(`/student/update/${s.id}`)}>Edit</button>
                <button onClick={() => deleteStudent(s.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Students;
