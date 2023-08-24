import { Route, Routes } from 'react-router';
import Login from './Login/Login';
import Dashboard from './Dashboard/Dashboard';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route exact path="/" element={<Login/>}/>
        <Route exact path='/dashboard' element={<Dashboard/>}/>
      </Routes>
    </div>
  );
}

export default App;
