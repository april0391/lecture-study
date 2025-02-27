import MainNavigation from "./MainNavigation";
import { Outlet } from "react-router-dom";
import classes from "./Layout.module.css";

const Layout = ({ children }) => {
  return (
    <>
      <MainNavigation />
      <main className={classes.main}>{children}</main>
    </>
  );
};

export default Layout;
