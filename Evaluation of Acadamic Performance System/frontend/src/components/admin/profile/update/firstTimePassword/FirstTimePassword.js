import React from "react";
import Body from "./Body";

import Header from "../../../Header";

const FirstTimePassword = () => {
  return (
    <div >
      <div className="flex flex-col  bg-[#f4f6fa] rounded-2xl shadow-2xl space-y-6 ">
        <Header />
        <div className="flex flex-[0.95] w-full">
          <Body />
        </div>
      </div>
    </div>
  );
};

export default FirstTimePassword;
