import axios from 'axios';

export const axiosInstance = axios.create({
  // baseURL: 'http://172.16.5.246:8080/',
  baseURL: 'http://localhost:8080/',
  timeout: 10000,
  withCredentials: true,
});
