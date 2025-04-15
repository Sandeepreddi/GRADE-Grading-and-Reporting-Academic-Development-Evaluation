import React, { useEffect, useState } from "react";

const Body = () => {
  const [cards, setCards] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/parking/all")
      .then((res) => res.json())
      .then((data) => {
        setCards(data);
      })
      .catch((err) => console.error("Error loading parking data:", err));
  }, []);

  return (
    <div style={{ display: "grid", gridTemplateColumns: "repeat(auto-fit, minmax(150px, 1fr))", gap: "1rem", padding: "1rem" }}>
      {cards.map((card, index) => (
        <div key={index} style={{ border: "1px solid #ccc", borderRadius: "8px", padding: "1rem", textAlign: "center" }}>
          <h3>Parking #{card.parkingid}</h3>
          <p>Status: <strong>{card.status}</strong></p>
          <p>Flat No: {card.flatNo || "-"}</p>
          <p>Block: {card.block || "-"}</p>
        </div>
      ))}
    </div>
  );
};

export default Body;
