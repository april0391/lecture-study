import React from "react";
import { FaUserAlt, FaLaptop, FaBell } from "react-icons/fa";

const Dashboard2 = () => {
  return (
    <div className="min-h-screen flex flex-col">
      {/* Header */}
      <header className="bg-gray-800 text-white p-4">
        <div className="text-2xl font-semibold pl-4">My Dashboard</div>
      </header>

      <div className="flex flex-1">
        {/* Sidebar */}
        <aside className="bg-gray-700 text-white w-64 p-4">
          <ul>
            <li className="py-2 hover:bg-gray-600 rounded">
              <FaUserAlt className="inline mr-2" />
              Dashboard
            </li>
            <li className="py-2 hover:bg-gray-600 rounded">
              <FaLaptop className="inline mr-2" />
              Devices
            </li>
            <li className="py-2 hover:bg-gray-600 rounded">
              <FaBell className="inline mr-2" />
              Notifications
            </li>
          </ul>
        </aside>

        {/* Main Content */}
        <main className="flex-1 p-6">
          <div className="grid grid-cols-3 gap-6">
            {/* Stat Cards */}
            <div className="bg-white p-6 rounded-lg shadow-md">
              <h3 className="text-xl font-medium">Total Users</h3>
              <p className="text-4xl font-semibold text-gray-800">112,893</p>
              <div className="flex items-center text-sm text-gray-500">
                <FaUserAlt className="mr-1" />
                <span>Users</span>
              </div>
            </div>
            <div className="bg-white p-6 rounded-lg shadow-md">
              <h3 className="text-xl font-medium">New Signups</h3>
              <p className="text-4xl font-semibold text-gray-800">2,334</p>
              <div className="flex items-center text-sm text-gray-500">
                <FaLaptop className="mr-1" />
                <span>Signups</span>
              </div>
            </div>
            <div className="bg-white p-6 rounded-lg shadow-md">
              <h3 className="text-xl font-medium">Notifications</h3>
              <p className="text-4xl font-semibold text-gray-800">21</p>
              <div className="flex items-center text-sm text-gray-500">
                <FaBell className="mr-1" />
                <span>Alerts</span>
              </div>
            </div>
          </div>

          {/* Latest Updates */}
          <div className="bg-white p-6 rounded-lg shadow-md mt-6">
            <h3 className="text-xl font-medium">Latest Updates</h3>
            <p className="mt-2 text-gray-600">
              Recent updates will be shown here...
            </p>
          </div>
        </main>
      </div>
    </div>
  );
};

export default Dashboard2;
