import { Avatar, AvatarFallback } from "@/components/ui/avatar";
import MenuItem from "./menu-item";
import MenuTitle from "./menu-title";
import Link from "next/link";
import { LightDarkToggle } from "@/components/light-dark-toggle";

export default function MainMenu() {
  return (
    <nav className="bg-muted flex flex-col overflow-auto p-4">
      <header className="border-b border-b-zinc-300 pb-4 dark:border-b-black">
        <MenuTitle />
      </header>
      <ul className="grow py-4">
        <MenuItem href="/dashboard">My dashboard</MenuItem>
        <MenuItem href="/dashboard/teams">Teams</MenuItem>
        <MenuItem href="/dashboard/employees">Employees</MenuItem>
        <MenuItem href="/dashboard/account">Account</MenuItem>
        <MenuItem href="/dashboard/settings">Settings</MenuItem>
      </ul>
      <footer className="flex items-center gap-2">
        <Avatar>
          <AvatarFallback className="bg-pink-300 dark:bg-pink-800">
            TP
          </AvatarFallback>
        </Avatar>
        <Link className="hover:underline" href={"/logout"}>
          Logout
        </Link>
        <LightDarkToggle className="ml-auto" />
      </footer>
    </nav>
  );
}
