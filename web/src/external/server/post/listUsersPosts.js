import { axiosInstance } from '../_base/axiosInstance';
import { POST_URL } from '../_base/urls';

export async function listUsersPosts(token) {
  const URL = `${POST_URL}/mine?size=20&sort=publicationTime,asc`;

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
