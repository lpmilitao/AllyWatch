import { axiosInstance } from '../_base/axiosInstance';
import { COMMENT_URL, POST_URL } from '../_base/urls';

export async function commentOnPost(token, postId, comment) {
  const URL = `${POST_URL}/${postId}${COMMENT_URL}`;

  const response = await axiosInstance.post(
    URL,
    { comment },
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    },
  );
  return response.data;
}
