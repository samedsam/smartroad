import { FormEvent, useState } from 'react';
import { backendUrl } from '../config/api';

interface OptimizeResponse {
  [key: string]: unknown;
}

function OptimizeRoutePanel() {
  const [fromLatitude, setFromLatitude] = useState(48.0);
  const [fromLongitude, setFromLongitude] = useState(2.0);
  const [toLatitude, setToLatitude] = useState(43.0);
  const [toLongitude, setToLongitude] = useState(1.0);
  const [valueOfTime, setValueOfTime] = useState(10);
  const [maxExtraMinutes, setMaxExtraMinutes] = useState(30);
  const [maxDetourKm, setMaxDetourKm] = useState(50);
  const [complexityFactor, setComplexityFactor] = useState(1);

  const [loading, setLoading] = useState(false);
  const [response, setResponse] = useState<OptimizeResponse | null>(null);
  const [error, setError] = useState<string | null>(null);

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    setLoading(true);
    setResponse(null);
    setError(null);

    const payload = {
      from: {
        latitude: Number(fromLatitude),
        longitude: Number(fromLongitude),
      },
      to: {
        latitude: Number(toLatitude),
        longitude: Number(toLongitude),
      },
      userProfile: {
        valueOfTimeEuroPerHour: Number(valueOfTime),
        maxExtraMinutes: Number(maxExtraMinutes),
        maxDetourKm: Number(maxDetourKm),
        complexityFactor: Number(complexityFactor),
      },
    };

    try {
      const res = await fetch(`${backendUrl}/optimize-route`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      });

      if (!res.ok) {
        throw new Error('Request failed');
      }

      const data: OptimizeResponse = await res.json();
      setResponse(data);
    } catch (err) {
      console.error(err);
      setError('Could not reach optimizer. Check the payload or backend status.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="card">
      <h2>Custom Optimize (basic)</h2>
      <p className="description">Send a minimal optimization payload with origin, destination, and profile.</p>

      <form onSubmit={handleSubmit}>
        <div className="grid" style={{ gridTemplateColumns: '1fr 1fr', gap: '10px' }}>
          <label>
            From latitude
            <input
              type="number"
              value={fromLatitude}
              step="0.01"
              onChange={(e) => setFromLatitude(parseFloat(e.target.value))}
            />
          </label>
          <label>
            From longitude
            <input
              type="number"
              value={fromLongitude}
              step="0.01"
              onChange={(e) => setFromLongitude(parseFloat(e.target.value))}
            />
          </label>
          <label>
            To latitude
            <input
              type="number"
              value={toLatitude}
              step="0.01"
              onChange={(e) => setToLatitude(parseFloat(e.target.value))}
            />
          </label>
          <label>
            To longitude
            <input
              type="number"
              value={toLongitude}
              step="0.01"
              onChange={(e) => setToLongitude(parseFloat(e.target.value))}
            />
          </label>
        </div>

        <label>
          Value of time (€ / hour)
          <input
            type="number"
            value={valueOfTime}
            step="0.5"
            onChange={(e) => setValueOfTime(parseFloat(e.target.value))}
          />
        </label>

        <label>
          Max extra minutes
          <input
            type="number"
            value={maxExtraMinutes}
            onChange={(e) => setMaxExtraMinutes(parseInt(e.target.value, 10))}
          />
        </label>

        <label>
          Max detour (km)
          <input
            type="number"
            value={maxDetourKm}
            step="1"
            onChange={(e) => setMaxDetourKm(parseFloat(e.target.value))}
          />
        </label>

        <label>
          Complexity factor
          <input
            type="number"
            value={complexityFactor}
            step="0.1"
            onChange={(e) => setComplexityFactor(parseFloat(e.target.value))}
          />
        </label>

        <button type="submit" disabled={loading}>
          {loading ? 'Sending...' : 'Send to optimizer'}
        </button>

        {error && <div className="status error">{error}</div>}
        {response && (
          <div className="response-block">
            <pre>{JSON.stringify(response, null, 2)}</pre>
          </div>
        )}
      </form>
    </div>
  );
}

export default OptimizeRoutePanel;
