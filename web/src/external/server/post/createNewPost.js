import { axiosInstance } from '../_base/axiosInstance';
import { POST_URL } from '../_base/urls';

export async function createNewPost(
  token,
  { title, body, aggressor, anonymous },
) {
  const response = await axiosInstance.post(
    POST_URL,
    {
      title,
      body,
      aggressor,
      anonymous,
    },
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    },
  );
  return response.data;
}
