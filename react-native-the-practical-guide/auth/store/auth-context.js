import { createContext, useEffect, useState } from "react";
import AsyncStorage from "@react-native-async-storage/async-storage";

export const AuthContext = createContext({
  token: "",
  isAuthenticated: false,
  authenticate: (token) => {},
  logout: () => {},
});

export default function AuthContextProvider({ children }) {
  const [authToken, setAuthToken] = useState();

  function authenticate(token) {
    AsyncStorage.setItem("token", token);
    setAuthToken(token);
  }

  function logout() {
    AsyncStorage.removeItem("token");
    setAuthToken(null);
  }

  return (
    <AuthContext
      value={{
        token: authToken,
        isAuthenticated: !!authToken,
        authenticate,
        logout,
      }}
    >
      {children}
    </AuthContext>
  );
}
