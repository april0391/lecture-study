/* fetch("http://localhost:8080/api")
  .then((res) => res.text())
  .then((data) => console.log(data)); */

const fetchData = async () => {
  const res = await fetch("http://localhost:8080/api");
  const data = await res.text();
};

const axios = require("axios");

const axiosData = async () => {
  const res = await axios.get("http://localhost:8080/api");
  console.log(res);
  console.log(res.data);
};

// fetchData();
axiosData();
