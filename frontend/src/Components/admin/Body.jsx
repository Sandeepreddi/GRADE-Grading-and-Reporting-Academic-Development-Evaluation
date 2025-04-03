import React, { useEffect, useState } from "react";
import "./AdminHome.css"; // Import the CSS file

const Body = () => {
  const [blocks, setBlocks] = useState(0);
  const [flats, setFlats] = useState(0);
  const [occupied, setOccupied] = useState(3); // Example static value
  const [people, setPeople] = useState(3); // Example static value

  useEffect(() => {
    fetch("http://localhost:8080/static/get")
      .then(response => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then(data => {
        const fetchedBlocks = data.blocks;
        setBlocks(fetchedBlocks);
        setFlats(fetchedBlocks * 20);
      })
      .catch(error => {
        console.error("Error fetching blocks:", error);
      });
  }, []);

  const cards = [
    { value: blocks, text: "Total no of Blocks", color: "green", icon: "🏢" },
    { value: flats, text: "Total no of Flats", color: "blue", icon: "🏠" },
    { value: occupied, text: "Total no of Flats Occupied", color: "purple", icon: "🏛️" },
    { value: people, text: "Total no of People in the Society", color: "orange", icon: "👥" }
  ];

  return (
    <div className="dashboard-container">
      {cards.map((card, index) => (
        <div key={index} className={`card ${card.color}`}>
          <h1>{card.value}</h1>
          <p>{card.text}</p>
          <span>{card.icon}</span>
        </div>
      ))}
    </div>
  );
};

export default Body;
