import { useNavigate, NavLink } from 'react-router-dom';
import './AdminHome.css';
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
          to="/admin/home"
          className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
        >
          
          <h2><FaHome className="icon" />  Dashboard</h2>
        </NavLink>
        
        <NavLink
          to="/admin/services"
          className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
        >
          
          
          <h2><FaTools className="icon" />  Request Services</h2>
        </NavLink>
        
        <NavLink
          to="/admin/complaints"
          className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
        >
          
          <h2><FaExclamationCircle className="icon" />  Complaints</h2>
        </NavLink>
        <NavLink
          to="/admin/events"
          className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
        >
          
          <h2><FaCalendarAlt className="icon" /> Events</h2>
        </NavLink>
        <NavLink
          to="/admin/notices"
          className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
        >
          
          <h2><FaBell className="icon" />  Notices</h2>
        </NavLink>
        <NavLink
          to="/admin/posts"
          className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
        >
          
          <h2><FaNewspaper className="icon" />  Posts</h2>
        </NavLink>
        <NavLink
          to="/admin/parking"
          className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
        >
          
          <h2><FaCar className="icon" />  Parking</h2>
        </NavLink>
        <NavLink
          to="/admin/emergency"
          className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
        >
          
          <h2><FaPhoneAlt className="icon" />  Emergency Contacts</h2>
        </NavLink>
        <NavLink
          to="/admin/billing"
          className={({ isActive }) => (isActive ? 'Nav_text active' : 'Nav_text')}
        >
          
          <h2><FaMoneyBillAlt className="icon" />  Billings</h2>
        </NavLink>
  
        {/* Logout Button */}
        <button className="button" onClick={() => navigate('/login')}>
          Logout
        </button>
      </div>
    );
}

export default SideNav;
