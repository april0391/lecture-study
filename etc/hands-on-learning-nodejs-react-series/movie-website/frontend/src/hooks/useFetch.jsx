import axios from "axios";

const fetchData = async (httpMethod, url, data, config) => {
  return await axios({
    method: httpMethod,
    url: url,
    data: data,
    ...config,
  });
};

const useFetch = () => {
  return fetchData;
};

export default useFetch;
