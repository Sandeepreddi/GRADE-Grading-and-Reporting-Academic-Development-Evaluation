import React, { useState } from "react";
import { useEffect } from "react";




function AIDashboard() {
  const [message, setMessage] = useState("");
  const [messages, setMessages] = useState([]); // Store all messages
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    import("./AI.css");
  }, []);
  

  const sendMessage = async () => {
    if (!message.trim()) return; // Don't send empty messages

    setLoading(true);
    const userMessage = { text: message, sender: "user" };
    setMessages((prev) => [...prev, userMessage]); // Add user message to the chat

    try {
      const res = await fetch("http://localhost:5001/api/chat", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ message }),
      });

      const data = await res.json();
      const botMessage = { text: data.response, sender: "bot" };
      setMessages((prev) => [...prev, botMessage]); // Add bot response to the chat
    } catch (error) {
      console.error("Error fetching response:", error);
      const errorMessage = { text: "Error getting response.", sender: "bot" };
      setMessages((prev) => [...prev, errorMessage]); // Add error message to the chat
    } finally {
      setLoading(false);
      setMessage(""); // Clear input after sending
    }
  };

  const handleKeyPress = (e) => {
    if (e.key === "Enter") {
      sendMessage();
    }
  };
  

  return (
    <div className="app-container">
      <h1>College Chatbot</h1>
      <div className="chat-container">
        <div className="chat-box">
          {messages.map((msg, index) => (
            <div
              key={index}
              className={`message ${msg.sender === "user" ? "user-message" : "bot-message"}`}
            >
              <p>{msg.text}</p>
            </div>
          ))}
          {loading && (
            <div className="message bot-message">
              <p>Loading...</p>
            </div>
          )}
        </div>
        <div className="input-container">
          <input
            type="text"
            placeholder="Ask something..."
            value={message}
            onChange={(e) => setMessage(e.target.value)}
            onKeyPress={handleKeyPress}
            className="message-input"
          />
          <button onClick={sendMessage} className="send-button">
            Send
          </button>
        </div>
      </div>
    </div>
  );
}

export default AIDashboard;