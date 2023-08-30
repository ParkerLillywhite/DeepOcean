import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Visualizer from './Visualizer/Visualizer';
import Home from "./Home/Home"

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<Home />}/>
          <Route path="visualizer" element={<Visualizer />}/>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
