import React, { useEffect, useState } from "react";
import "./Complaints.css";
import { FaTrash } from "react-icons/fa"; 




function DisplayComplaints() {
  const [complaints, setComplaints] = useState([]);
  const [selectedComplaint, setSelectedComplaint] = useState(null);

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
    <div className="resident-complaints-list">
      <h3>Complaints List</h3>

      {complaints.length === 0 ? (
        <p>No complaints found.</p>
      ) : (
        <ul style={{ padding: "0px" }}>
          {complaints.map((complaint) => (
            <li key={complaint.id} className="resident-service-item">
              <div>
                <strong>{complaint.title}</strong>
              </div>
              <div className="resident-service-buttons">
                <button
                  className="resident-services-view-button"
                  onClick={() => setSelectedComplaint(complaint)}
                >
                  View
                </button>
                <button
                  className="resident-services-delete-button"
                  onClick={() => handleDelete(complaint.id)}
                >
                  Delete
                </button>
              </div>
            </li>
          ))}
        </ul>
      )}

      {/* Popup Modal */}
      {selectedComplaint && (
        <div className="resident-complaints-modal-overlay">
          <div className="resident-complaints-modal-content">
              <div  onClick={() => setSelectedComplaint(null)}>
                <div  onClick={(e) => e.stopPropagation()}>
                  <h4>{selectedComplaint.title}</h4>
                  <p>{selectedComplaint.description}</p>
                  <button  className="close-card-button" onClick={() => setSelectedComplaint(null)}>Close</button>
                </div>
              </div>
            </div>
        </div>
      )}
    </div>
  );
}

export default DisplayComplaints;
