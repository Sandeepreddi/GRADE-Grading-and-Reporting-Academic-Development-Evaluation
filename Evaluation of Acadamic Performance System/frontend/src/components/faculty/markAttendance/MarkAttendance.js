import React, { useEffect } from "react";
import Body from "./Body";
import Header from "../Header";
import Sidebar from "../Sidebar";
import { useDispatch } from "react-redux";
import {
  getAllDepartment,
  getAllSubject,
} from "../../../redux/actions/adminActions";

const MarkAttendance = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getAllDepartment());
  }, [dispatch]);
  return (
    <div >
      <div className="flex flex-col  bg-[#f4f6fa]  rounded-2xl shadow-2xl space-y-6 ">
        <Header />
        <div className="flex flex-[0.95]">
          <Sidebar />
          <Body />
        </div>
      </div>
    </div>
  );
};

export default MarkAttendance;
