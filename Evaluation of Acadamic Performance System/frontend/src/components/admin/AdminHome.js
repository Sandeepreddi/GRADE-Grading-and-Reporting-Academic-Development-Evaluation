import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import {
  getAllStudent,
  getAllFaculty,
  getAllAdmin,
  getAllDepartment,
  getNotice,
} from "../../redux/actions/adminActions";
import Body from "./Body";
import Header from "./Header";
import Sidebar from "./Sidebar";

const AdminHome = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getAllStudent());
    dispatch(getAllFaculty());
    dispatch(getAllAdmin());
    dispatch(getAllDepartment());
    dispatch(getNotice());
  }, [dispatch]);
  return (
    <div >
      <div className="flex flex-col  bg-[#f4f6fa]  rounded-2xl shadow-2xl space-y-6 overflow-y-hidden">
        <Header />
        <div className="flex flex-[0.95]">
          <Sidebar />
          <Body />
        </div>
      </div>
    </div>
  );
};

export default AdminHome;
