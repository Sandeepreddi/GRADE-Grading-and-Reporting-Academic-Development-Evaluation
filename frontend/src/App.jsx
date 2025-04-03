import Login from './Components/Login'
import './App.css'
import Signup from './Components/Signup'
import FirsttymAdmin from './Components/admin/FirstTym'
import FirstTymUser from './Components/resident/FirstTym'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AdminHome from './Components/admin/AdminHome'
import ResidentHome from './Components/resident/ResidentHome'


import AdminBilling from './Components/admin/billing/AdminBilling'
import AdminComplaints from './Components/admin/complaints/AdminComplaints'
import AdminEmergency from './Components/admin/emergency_contacts/AdminEmergency'
import AdminService from './Components/admin/request_services/AdminService'
import AdminEvents from './Components/admin/events/AdminEvents'
import AdminNotice from './Components/admin/notices/AdminNotice'
import AdminParking from './Components/admin/parking/AdminParking'
import Adminposts from './Components/admin/posts/Adminposts'



import ResidentBilling from './Components/resident/billing/ResidentBilling'
import ResidentComplaints from './Components/resident/complaints/ResidentComplaints'
import ResidentEmergency from './Components/resident/emergency_contacts/ResidentEmergency'
import ResidentEvents from './Components/resident/events/ResidentEvents'
import ResidentNotice from './Components/resident/notices/ResidentNotice'
import ResidentParking from './Components/resident/parking/ResidentParking'
import Residentposts from './Components/resident/posts/Residentposts'
import ResidentService from './Components/resident/request_services/ResidentService'



function App() {
  

  return (
    <>
    <Router>
      <Routes>

        {/* Signup & Login */}
        <Route path="/" element={<Signup />} />
        <Route path="/login" element={<Login />} />

        {/* Forms */}
        <Route path="/admin-first-time" element={<FirsttymAdmin />} />
        <Route path="/user-first-time" element={<FirstTymUser />} />

        {/* Admin Routes */}
        <Route path="/admin/home" element={<AdminHome />} />
        <Route path="/admin/billing" element={<AdminBilling />} />
        <Route path="/admin/complaints" element={<AdminComplaints />} />
        <Route path="/admin/emergency" element={<AdminEmergency />} />
        <Route path="/admin/services" element={<AdminService />} />
        <Route path="/admin/events" element={<AdminEvents />} />
        <Route path="/admin/notices" element={<AdminNotice />} />
        <Route path="/admin/parking" element={<AdminParking />} />
        <Route path="/admin/posts" element={<Adminposts />} />

        {/* Resident Routes */}
        <Route path="/resident/home" element={<ResidentHome />} />
        <Route path="/resident/billing" element={<ResidentBilling />} />
        <Route path="/resident/complaints" element={<ResidentComplaints />} />
        <Route path="/resident/emergency" element={<ResidentEmergency />} />
        <Route path="/resident/events" element={<ResidentEvents />} />
        <Route path="/resident/notices" element={<ResidentNotice />} />
        <Route path="/resident/parking" element={<ResidentParking />} />
        <Route path="/resident/posts" element={<Residentposts />} />
        <Route path="/resident/services" element={<ResidentService />} />

      </Routes>
    </Router>
</>

  )
}

export default App
