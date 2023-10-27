import { axiosInstance } from '../_base/axiosInstance';
import { POST_URL, REPORT_URL } from '../_base/urls';

export async function reportPost(token, postId, body) {
  const URL = `${POST_URL}/${postId}${REPORT_URL}`;

  const response = await axiosInstance.post(
    URL,
    { body },
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    },
  );
  return response.data;
}
