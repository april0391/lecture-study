"use client";

import { cn } from "@/lib/utils";
import Link from "next/link";
import { usePathname } from "next/navigation";

type Props = {
  children: React.ReactNode;
  href: string;
};

export default function MenuItem({ children, href }: Props) {
  const pathname = usePathname();
  const isActive = pathname === href;

  return (
    <li>
      <Link
        className={cn(
          "text-muted-foreground hover:text-foreground block rounded-md p-2 hover:bg-white dark:hover:bg-zinc-700",
          isActive &&
            "bg-primary hover:bg-primary dark:hover:bg-primary hover:text-primary-foreground text-primary-foreground",
        )}
        href={href}
      >
        {children}
      </Link>
    </li>
  );
}
