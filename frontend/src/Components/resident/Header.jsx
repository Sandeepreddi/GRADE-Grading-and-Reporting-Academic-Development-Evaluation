import { useState, useEffect } from "react";
import "./ResidentHome.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser, faEdit } from "@fortawesome/free-solid-svg-icons";

function Header() {
  const email = localStorage.getItem("email"); // Get email from localStorage
  console.log(email);
  const [resident, setResident] = useState(null); // Store resident data
  const [error, setError] = useState(null);
  const [showCard, setShowCard] = useState(false); // Toggle profile card
  const [editMode, setEditMode] = useState(false); // Toggle edit mode
  const [updatedData, setUpdatedData] = useState({}); // Store form data

  useEffect(() => {
    if (email) {
      fetch(`http://localhost:8080/residents/${email}`)
        .then((response) => {
          if (!response.ok) {
            throw new Error("resident not found");
          }
          return response.json();
        })
        .then((data) => {
          setResident(data);
          setUpdatedData(data); // Initialize form data with current values
        })
        .catch((err) => setError(err.message));
    }
  }, [email]);

  // Toggle profile card
  const handleIconClick = () => {
    setShowCard(!showCard);
    setEditMode(false); // Close edit mode if profile card is closed
  };

  // Toggle edit mode
  const handleEditClick = () => {
    setEditMode(true);
  };

  // Handle form input changes
  const handleChange = (e) => {
    setUpdatedData({ ...updatedData, [e.target.name]: e.target.value });
  };

  // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();
    fetch(`http://localhost:8080/residents/update/${email}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatedData),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to update resident details");
        }
        return response.json();
      })
      .then((data) => {
        setResident(data); // Update state with new data
        setEditMode(false); // Exit edit mode
      })
      .catch((err) => alert(err.message));
  };

  return (
    <div className="column1">
      <div className="header_img">
        <img src="/Community-2.png" alt="Community" />
      </div>

      {resident ? (
        <h3 className="column1">
          {resident.name}
          <FontAwesomeIcon
            icon={faUser}
            className="profile-icon"
            size="lg"
            onClick={handleIconClick}
            style={{ cursor: "pointer", marginLeft: "10px" }}
          />
        </h3>
      ) : (
        <h3 className="column1">{error || "Loading..."}</h3>
      )}

      {/* Profile Card */}
      {showCard && resident && (
        <div className="profile-card">
          {editMode ? (
            // Update Form
            <form onSubmit={handleSubmit}>
              <h3>Edit resident Details</h3>
              <input type="text" name="name" value={updatedData.name} onChange={handleChange} required />
              <input type="text" name="phone_number" value={updatedData.phone_number} onChange={handleChange} required />
              <input type="text" name="societyName" value={updatedData.societyName} onChange={handleChange} required />
              <input type="text" name="flatNo" value={updatedData.flatNo} onChange={handleChange} required />
              <input type="text" name="postal" value={updatedData.postal} onChange={handleChange} required />
              <button type="submit" className="save-btn">Save</button>
              <button onClick={() => setEditMode(false)} className="close-btn">Cancel</button>
            </form>
          ) : (
            // Display Profile Details
            <>
              <h3>{resident.name}</h3>
              <p><strong>Phone:</strong> {resident.phone_number}</p>
              <p><strong>Society Name:</strong> {resident.societyName}</p>
              <p><strong>Flat No:</strong> {resident.flatNo}</p>
              <p><strong>Postal Code:</strong> {resident.postal}</p>
              <p><strong>Email:</strong> {resident.email}</p>
              <button onClick={handleEditClick} className="edit-btn">
                <FontAwesomeIcon icon={faEdit} /> Edit
              </button>
              <button onClick={handleIconClick} className="close-btn">Close</button>
            </>
          )}
        </div>
      )}
    </div>
  );
}

export default Header;
