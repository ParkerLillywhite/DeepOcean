import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MicrophoneVisualizer from './Visualizer/MicrophoneVisualizer';
import Home from "./Home/Home"

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<Home />}/>
          <Route path="microphone-visualizer" element={<MicrophoneVisualizer />}/>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
