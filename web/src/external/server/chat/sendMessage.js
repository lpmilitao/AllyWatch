import { axiosInstance } from '../_base/axiosInstance';
import { CHAT_URL, USER_URL } from '../_base/urls';

export async function sendMessage(token, chatId, message) {
  const URL = `${USER_URL}${CHAT_URL}/${chatId}`;

  const response = await axiosInstance.post(
    URL,
    {
      message: message,
    },
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    },
  );
  return response.data;
}
