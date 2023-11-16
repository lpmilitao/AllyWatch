import axios from 'axios';
import { axiosInstance } from './_baseInstance';
import { OPEN_AI_API_KEY } from './apiKey';

export async function sendMessage(message) {
  axios
    .post(
      'https://api.openai.com/v1/engines/davinci/completions',
      {
        prompt: message,
        max_tokens: 200,
        temperature: 0.1,
        stop: ['.'],
      },
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${OPEN_AI_API_KEY}`,
        },
      },
    )
    .then((response) => {
      console.log(response.data.choices[0].text);
    })
    .catch((error) => {
      console.error('Erro na API:', error);
    });
}
