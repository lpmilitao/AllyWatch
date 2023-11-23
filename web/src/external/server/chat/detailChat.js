import { axiosInstance } from '../_base/axiosInstance';
import { CHAT_URL, USER_URL } from '../_base/urls';

export async function detailChat(token, chatId) {
  const URL = `${USER_URL}${CHAT_URL}/${chatId}`;

  const response = await axiosInstance.get(URL, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
}
