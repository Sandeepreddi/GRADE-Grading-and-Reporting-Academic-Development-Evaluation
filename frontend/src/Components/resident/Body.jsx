import React, { useEffect, useState } from 'react';
import ResidentCard from './ResidentCard';
import './ResidentHome.css';

function Body() {
  const [residents, setResidents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [expandedCard, setExpandedCard] = useState(null);

  useEffect(() => {
    fetch('http://localhost:8080/residents')
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch residents');
        }
        return response.json();
      })
      .then(data => {
        setResidents(data);
        setLoading(false);
      })
      .catch(error => {
        setError(error.message);
        setLoading(false);
      });
  }, []);

  const handleCardClick = (index) => {
    setExpandedCard(expandedCard === index ? null : index);
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div className="card-body">
      <h1>Resident DashBoard</h1>
      <div className="resident-list">
        {residents.map((resident, index) => (
          <ResidentCard
          key={index}
          name={resident.name}
          flat={resident.flatNo}
          phone={resident.phone_number}
          email={resident.email}
          expanded={expandedCard === index}
          onClick={() => handleCardClick(index)}
          societyName={resident.societyName}  // Specifically pass societyName
          postal={resident.postal}           // Specifically pass postal
        />
        ))}
      </div>
    </div>
  );
}

export default Body;