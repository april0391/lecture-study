"use client";

import Link from "next/link";
import classes from "./nav-link.module.css";
import { usePathname } from "next/navigation";

const NavLink = ({ href, children }) => {
  const path = usePathname();
  return (
    <Link
      href={href}
      className={`${classes.link} ${
        path.startsWith(href) ? classes.active : ""
      }`}
    >
      {children}
    </Link>
  );
};

export default NavLink;
