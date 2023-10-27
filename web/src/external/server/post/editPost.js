import { axiosInstance } from '../_base/axiosInstance';
import { POST_URL } from '../_base/urls';

export async function editPost(
  token,
  postId,
  { title, body, aggressor, anonymous },
) {
  const URL = `${POST_URL}/${postId}`;
  const response = await axiosInstance.put(
    URL,
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
