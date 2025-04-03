import { useState, useEffect } from "react";
import "./AdminHome.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser, faEdit } from "@fortawesome/free-solid-svg-icons";

function Header() {
  const email = localStorage.getItem("email"); // Get email from localStorage
  console.log(email);
  const [admin, setAdmin] = useState(null); // Store admin data
  const [error, setError] = useState(null);
  const [showCard, setShowCard] = useState(false); // Toggle profile card
  const [editMode, setEditMode] = useState(false); // Toggle edit mode
  const [updatedData, setUpdatedData] = useState({}); // Store form data

  useEffect(() => {
    if (email) {
      fetch(`http://localhost:8080/admins/${email}`)
        .then((response) => {
          if (!response.ok) {
            throw new Error("Admin not found");
          }
          return response.json();
        })
        .then((data) => {
          setAdmin(data);
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
    fetch(`http://localhost:8080/admins/update/${email}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatedData),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to update admin details");
        }
        return response.json();
      })
      .then((data) => {
        setAdmin(data); // Update state with new data
        setEditMode(false); // Exit edit mode
      })
      .catch((err) => alert(err.message));
  };

  return (
    <div className="column1">
      <div className="header_img">
        <img src="/Community-2.png" alt="Community" />
      </div>

      {admin ? (
        <h3 className="column1">
          {admin.name}
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
      {showCard && admin && (
        <div className="profile-card">
          {editMode ? (
            // Update Form
            <form onSubmit={handleSubmit}>
              <h3>Edit Admin Details</h3>
              <input type="text" name="name" value={updatedData.name} onChange={handleChange} required />
              <input type="text" name="phone_number" value={updatedData.phone_number} onChange={handleChange} required />
              <input type="text" name="societyName" value={updatedData.societyName} onChange={handleChange} required />
              <input type="text" name="societyAddress" value={updatedData.societyAddress} onChange={handleChange} required />
              <input type="text" name="city" value={updatedData.city} onChange={handleChange} required />
              <input type="text" name="district" value={updatedData.district} onChange={handleChange} required />
              <input type="text" name="postal" value={updatedData.postal} onChange={handleChange} required />
              <button type="submit" className="save-btn">Save</button>
              <button onClick={() => setEditMode(false)} className="close-btn">Cancel</button>
            </form>
          ) : (
            // Display Profile Details
            <>
              <h3>{admin.name}</h3>
              <p><strong>Phone:</strong> {admin.phone_number}</p>
              <p><strong>Society Name:</strong> {admin.societyName}</p>
              <p><strong>Society Address:</strong> {admin.societyAddress}</p>
              <p><strong>City:</strong> {admin.city}</p>
              <p><strong>District:</strong> {admin.district}</p>
              <p><strong>Postal Code:</strong> {admin.postal}</p>
              <p><strong>Email:</strong> {admin.email}</p>
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
