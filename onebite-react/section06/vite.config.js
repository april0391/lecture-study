import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000, // 다른 포트로 변경 (예: 3000)
    host: "127.0.0.1", // IPv4를 강제로 사용
  },
});
