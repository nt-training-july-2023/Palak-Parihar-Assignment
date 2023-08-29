import { Route, Routes } from 'react-router';
import Login from './Login/Login';
import Dashboard from './Dashboard/Dashboard';
import EmployeeRegistration from './EmployeeRegistration/EmployeeRegistration';
import NewTicket from './NewTicket/NewTicket';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route exact path="/" element={<Login />} />
        <Route exact path='/dashboard' element={<Dashboard />} />
        <Route exact path='/registration' element={<EmployeeRegistration />} />
        <Route exact path='/newticket' element={<NewTicket/>}/>
      </Routes>
    </div>
  );
}

export default App;
