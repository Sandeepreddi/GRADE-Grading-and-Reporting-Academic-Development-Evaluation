import React from 'react';
import './ResidentHome.css';
import { useNavigate, NavLink } from 'react-router-dom';
import {
  FaHome,
  FaTools,
  FaExclamationCircle,
  FaCalendarAlt,
  FaBell,
  FaNewspaper,
  FaCar,
  FaPhoneAlt,
  FaMoneyBillAlt,
} from 'react-icons/fa';

function SideNav() {
  const navigate = useNavigate();

  return (
    <div className='Nav_section'>
      <NavLink
        to="/resident/home"
        className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
      >
        <FaHome className="icon" />
        <h2>Dashboard</h2>
      </NavLink>
      <NavLink
        to="/resident/services"
        className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
      >
        <FaTools className="icon" />
        <h2>Request Services</h2>
      </NavLink>
      <NavLink
        to="/resident/complaints"
        className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
      >
        <FaExclamationCircle className="icon" />
        <h2>Complaints</h2>
      </NavLink>
      <NavLink
        to="/resident/events"
        className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
      >
        <FaCalendarAlt className="icon" />
        <h2>Events</h2>
      </NavLink>
      <NavLink
        to="/resident/notices"
        className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
      >
        <FaBell className="icon" />
        <h2>Notices</h2>
      </NavLink>
      <NavLink
        to="/resident/posts"
        className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
      >
        <FaNewspaper className="icon" />
        <h2>Posts</h2>
      </NavLink>
      <NavLink
        to="/resident/parking"
        className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
      >
        <FaCar className="icon" />
        <h2>Parking</h2>
      </NavLink>
      <NavLink
        to="/resident/emergency"
        className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
      >
        <FaPhoneAlt className="icon" />
        <h2>Emergency Contacts</h2>
      </NavLink>
      <NavLink
        to="/resident/billing"
        className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
      >
        <FaMoneyBillAlt className="icon" />
        <h2>Billings</h2>
      </NavLink>

      {/* Logout Button */}
      <button className="button" onClick={() => navigate('/login')}>
        Logout
      </button>
    </div>
  );
}

export default SideNav;