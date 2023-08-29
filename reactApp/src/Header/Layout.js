import React from "react";
import Header from "./Header";

const Layout = () => {
    return (
        <div>
            <Header />
            {props.children}
        </div>
    )
}

export default Layout;