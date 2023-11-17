import axios from 'axios';
import { axiosInstance } from './_baseInstance';
import { OPEN_AI_API_KEY } from './apiKey';

export async function sendMessage(messages) {
  try {
    const response = await axios.post(
      'https://api.openai.com/v1/chat/completions',
      {
        messages: messages,
        model: 'gpt-3.5-turbo',
        max_tokens: 200000,
        temperature: 0.1,
      },
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${OPEN_AI_API_KEY}`,
        },
      },
    );

    return response.data.choices[0].text;
  } catch (error) {
    console.error('Erro na API:', error);
    throw error;
  }
}
