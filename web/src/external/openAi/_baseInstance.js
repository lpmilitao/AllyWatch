import axios from 'axios';
import { OPEN_AI_API_KEY } from './apiKey';

export const axiosInstance = axios.create({
  baseURL: 'https://api.openai.com/v1/engines/davinci/completions',
  headers: {
    Authorization: `Bearer ${OPEN_AI_API_KEY}`,
    'Content-Type': 'application/json',
  },
});
