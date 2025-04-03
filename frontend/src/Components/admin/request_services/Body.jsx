import React, { useEffect, useState } from "react";
import "./AdminService.css"; // Import CSS file

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

  return (
    <div className="service-list">
      <h3>Service Requests</h3>
      {services.length === 0 ? (
        <p>No services available.</p>
      ) : (
        <ul>
          {services.map((service) => (
            <li key={service.id} className="service-item">
              <div>
                <strong>{service.name}</strong> - {service.service}
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default DisplayServices;
