import React, { useEffect, useState } from "react";
import "./Complaints.css";

function DisplayComplaints() {
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

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this complaint?")) return;

    try {
      const response = await fetch(`http://localhost:8080/complaints/delete/${id}`, { method: "DELETE" });
      if (!response.ok) throw new Error("Failed to delete complaint");

      alert("Complaint deleted successfully!");
      fetchComplaints();
    } catch (error) {
      console.error(error);
      alert("Error deleting complaint.");
    }
  };

  return (
    <div className="complaints-list">
      <h2>Complaints List</h2>
      {complaints.length === 0 ? (
        <p>No complaints yet.</p>
      ) : (
        complaints.map((complaint) => (
          <div key={complaint.id} className="complaint-card">
            <h4>{complaint.title}</h4>
            <p>{complaint.description}</p>
            <button className="delete-button" onClick={() => handleDelete(complaint.id)}>🗑️</button>
          </div>
        ))
      )}
    </div>
  );
}

export default DisplayComplaints;
