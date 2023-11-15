import { PSYCHOLOGIST_URL, SPECIALIST_URL } from '../../_base/urls';
import { axiosInstance } from '../../_base/axiosInstance';

export async function listPsychologists(token, page) {
  const URL = `${SPECIALIST_URL}${PSYCHOLOGIST_URL}?status=APPROVED&page=${page}&size=10`;

  const response = await axiosInstance.get(URL, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
}
