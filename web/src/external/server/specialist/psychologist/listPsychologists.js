import { PSYCHOLOGIST_URL, SPECIALIST_URL } from '../../_base/urls';
import { axiosInstance } from '../../_base/axiosInstance';

export async function listPsychologists(token, status) {
  const URL = `${SPECIALIST_URL}${PSYCHOLOGIST_URL}?status=${status}`;

  const response = await axiosInstance.get(
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
