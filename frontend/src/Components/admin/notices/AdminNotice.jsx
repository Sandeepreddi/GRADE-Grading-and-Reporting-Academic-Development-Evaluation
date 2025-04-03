import React from 'react'
import Header from './../Header'
import SideNav from './../SideNav'
import Body from './Body'
import './../AdminHome.css'
import DisplayNotice from './DisplayNotice'

function AdminNotice() {
  return (
    <div>
        <Header /> 
        <div className="admin-container">
            <SideNav />
            <Body />
            <DisplayNotice />
        </div>
    </div>
  )
}

export default AdminNotice
