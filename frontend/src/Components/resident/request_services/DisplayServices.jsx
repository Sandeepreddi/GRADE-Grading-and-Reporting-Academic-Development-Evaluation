import React, { useEffect, useState } from "react";
import "./ResidentServices.css";

function DisplayServices() {
  const [services, setServices] = useState([]);

  useEffect(() => {
    fetchServices();
  }, []);

  const fetchServices = async () => {
    try {
      const response = await fetch("http://localhost:8080/service/all");
      if (!response.ok) throw new Error("Failed to fetch services");
      const data = await response.json();
      setServices(data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleDelete = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/service/delete/${id}`, { method: "DELETE" });
      if (!response.ok) throw new Error("Failed to delete service");
      setServices(services.filter((service) => service.id !== id));
      alert("Service deleted successfully!");
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="service-list">
      <h3>Service Requests</h3>
      {services.length === 0 ? <p>No services found.</p> : (
        <ul>
          {services.map((service) => (
            <li key={service.id} className="service-item">
              <div>
                <strong>{service.name}</strong> - {service.service}
              </div>
              <button className="view-button" onClick={() => alert(JSON.stringify(service, null, 2))}>View</button>
              <button className="delete-button" onClick={() => handleDelete(service.id)}>Delete</button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default DisplayServices;
