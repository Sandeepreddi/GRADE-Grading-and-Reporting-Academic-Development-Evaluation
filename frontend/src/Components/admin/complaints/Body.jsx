import React from 'react';
import { useState,useEffect } from 'react';
import './Complaints.css';


function Body() {
  const [complaints, setComplaints] = useState([]);
  
    useEffect(() => {
      fetchComplaints();
    }, []);
  
    const fetchComplaints = async () => {
      try {
        const response = await fetch("http://localhost:8080/complaints");
        const data = await response.json();
        setComplaints(data);
      } catch (error) {
        console.error("Error fetching complaints:", error);
      }
    };
  
    
  
    return (
      <div className="complaints-list">
        <h3>Complaints List</h3>
        {complaints.length === 0 ? (
          <p>No complaints yet.</p>
        ) : (
          complaints.map((complaint) => (
            <div key={complaint.id} className="complaint-card">
              <h4>{complaint.title}</h4>
              <p>{complaint.description}</p>
              
            </div>
          ))
        )}
      </div>
    );
}

export default Body;