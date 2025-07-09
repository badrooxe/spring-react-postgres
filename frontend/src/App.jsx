import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Students from './components/Students';
import NewStudent from './components/NewStudent';
import UpdateStudent from './components/UpdateStudent';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Students />} />
        <Route path="/student/add" element={<NewStudent />} />
        <Route path="/student/update/:id" element={<UpdateStudent />} />
      </Routes>
    </Router>
  );
}

export default App;
