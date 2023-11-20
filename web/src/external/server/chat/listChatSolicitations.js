import { axiosInstance } from '../_base/axiosInstance';
import { CHAT_URL, SOLICITATION_URL, USER_URL } from '../_base/urls';

export async function listChatsSolicitations(token) {
  const URL = `${USER_URL}${CHAT_URL}${SOLICITATION_URL}`;

  const response = await axiosInstance.get(URL, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
}
