import React, { useState } from "react";
import "./ResidentServices.css"; // Add CSS for styling
import DisplayServices from "./DisplayServices";


function Body() {
  const [showModal, setShowModal] = useState(false);
  const [name, setName] = useState("");
  const [address, setAddress] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [service, setService] = useState("");
  const [additionalNotes, setAdditionalNotes] = useState("");

  const handleSubmit = async () => {
    if (!name || !address || !phoneNumber || !service) {
      alert("Please fill all required fields.");
      return;
    }

    const serviceData = { name, address, phone_number: phoneNumber, service, additional_notes: additionalNotes };

    try {
      const response = await fetch("http://localhost:8080/service/create", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(serviceData),
      });

      if (!response.ok) throw new Error("Failed to create service");

      alert("Service created successfully!");
      setShowModal(false);
      setName("");
      setAddress("");
      setPhoneNumber("");
      setService("");
      setAdditionalNotes("");
    } catch (error) {
      console.error(error);
      alert("Error submitting service.");
    }
  };

  return (
    <div className="body">
      <h2>Admin Services</h2>
      <button className="create-button" onClick={() => setShowModal(true)}>Create Service</button>

      {showModal && (
        <div className="modal">
          <div className="modal-content">
            <h3>Create Service</h3>

            <label>Name:</label>
            <input type="text" value={name} onChange={(e) => setName(e.target.value)} />

            <label>Address:</label>
            <input type="text" value={address} onChange={(e) => setAddress(e.target.value)} />

            <label>Phone Number:</label>
            <input type="text" value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} />

            <label>Service Type:</label>
            <input type="text" value={service} onChange={(e) => setService(e.target.value)} />

            <label>Additional Notes:</label>
            <textarea value={additionalNotes} onChange={(e) => setAdditionalNotes(e.target.value)} />

            <button className="submit-button" onClick={handleSubmit}>Submit</button>
            <button className="cancel-button" onClick={() => setShowModal(false)}>Cancel</button>
          </div>
        </div>
      )}
      <DisplayServices></DisplayServices>
    </div>
  );
}

export default Body;
