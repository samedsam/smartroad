import DemoOptimizePanel from './components/DemoOptimizePanel';
import OptimizeRoutePanel from './components/OptimizeRoutePanel';
import SavingsSimulatorPanel from './components/SavingsSimulatorPanel';

function App() {
  return (
    <div className="container">
      <header>
        <h1>SmartRoad Playground</h1>
        <p>Play with route optimization, demo scenarios, and savings simulation.</p>
      </header>

      <div className="grid">
        <DemoOptimizePanel />
        <OptimizeRoutePanel />
        <SavingsSimulatorPanel />
      </div>
    </div>
  );
}

export default App;
