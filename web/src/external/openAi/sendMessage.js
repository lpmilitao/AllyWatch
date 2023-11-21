import axios from 'axios';
import { axiosInstance } from './_baseInstance';
import { OPEN_AI_API_KEY } from './apiKey';

export async function sendMessage(messages) {
  const response = await axios.post(
    'https://api.openai.com/v1/chat/completions',
    {
      messages: messages,
      model: 'gpt-3.5-turbo',
    },
    {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${OPEN_AI_API_KEY}`,
      },
    },
  );

  return response.data.choices.at(0).message.content;
}
