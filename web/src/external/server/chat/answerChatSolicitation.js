import { axiosInstance } from '../_base/axiosInstance';
import { CHAT_URL, SOLICITATION_URL, USER_URL } from '../_base/urls';

export async function answerChatSolicitation(token, accepted, solicitationId) {
  const URL = `${USER_URL}${CHAT_URL}${SOLICITATION_URL}/${solicitationId}?accepted=${accepted}`;

  const response = await axiosInstance.put(
    URL,
    {},
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    },
  );
  return response.data;
}
