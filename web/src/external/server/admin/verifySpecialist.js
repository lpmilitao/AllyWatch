import { axiosInstance } from '../_base/axiosInstance';
import { ADMIN_URL } from '../_base/urls';

export async function verifySpecialist(token, accepted, type, specialistId) {
  const URL = `${ADMIN_URL}/verify-specialist/${specialistId}`;

  const response = await axiosInstance.put(
    URL,
    {
      accepted: accepted,
      type: type,
    },
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    },
  );
  return response.data;
}
