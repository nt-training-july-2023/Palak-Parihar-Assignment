import Navigation from './Components/Navigation/Navigation';
import Main from './Main/Main';
import classes from './App.module.css';
import { Route, Routes } from 'react-router';
import Login from './Containers/Login/Login';


function App() {
  return (
    <div className={classes.App}>
      <Navigation/>
      <div className={classes.mainContainer}>
        <Main />
      </div>
    </div>
  );
}

export default App;
