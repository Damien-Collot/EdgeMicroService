import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { SnackbarProvider } from 'notistack';
import SignIn from './pages/SignIn';
import './assets/style/App.css';
import SignUp from './pages/SignUp';
import HomePage from './pages/HomePage';

function App() {
  return (
    <SnackbarProvider maxSnack={3}>
      <Router>
        <Routes>
          <Route path="/" element={<SignIn />} />
          <Route path="/signIn" element={<SignIn />} />
          <Route path="/signUp" element={<SignUp />} />
          <Route path="/home" element={<HomePage />} />
        </Routes>
      </Router>
    </SnackbarProvider>
  );
}

export default App;
