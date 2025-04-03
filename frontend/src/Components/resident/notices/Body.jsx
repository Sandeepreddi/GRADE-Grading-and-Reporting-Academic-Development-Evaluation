import React, { useState, useEffect } from 'react';
import './DisplayNotice.css'; // We'll create this CSS file

function DisplayNotice() {
  const [notices, setNotices] = useState([]);

  useEffect(() => {
    fetchNotices();
  }, []);

  const fetchNotices = async () => {
    try {
      const response = await fetch("http://localhost:8080/notice");
      if (!response.ok) throw new Error("Failed to fetch notices");
      const data = await response.json();
      setNotices(data);
    } catch (error) {
      console.error("Error fetching notices:", error);
    }
  };

  return (
    <div className="notice-container">
      <h2 className="notice-header">Resident Notices</h2>
      
      <div className="notice-grid">
        {notices.length > 0 ? (
          notices.map((notice) => (
            <div key={notice.id} className="notice-card">
              {notice.imageBase64 && (
                <img 
                  src={`data:image/png;base64,${notice.imageBase64}`} 
                  alt="Notice" 
                  className="notice-image" 
                />
              )}
              <div className="notice-content">
                <h3 className="notice-title">{notice.name}</h3>
                <div className="notice-meta">
                  <span className="notice-date">{notice.date}</span>
                  <span className="notice-time">{notice.time}</span>
                </div>
                <p className="notice-description">{notice.description}</p>
              </div>
            </div>
          ))
        ) : (
          <p className="no-notices">No notices available at the moment.</p>
        )}
      </div>
    </div>
  );
}

export default DisplayNotice;