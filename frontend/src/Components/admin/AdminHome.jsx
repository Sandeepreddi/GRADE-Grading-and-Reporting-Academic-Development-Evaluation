import React from 'react'
import Header from './Header'
import Body from './Body'
import SideNav from './SideNav'
import './AdminHome.css'

function AdminHome() {
  return (
    <div>
        <Header /> 
        <div className="admin-container">
            <SideNav />
            <Body />
        </div>
    </div>
  )
}

export default AdminHome
