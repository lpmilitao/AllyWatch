import { axiosInstance } from '../_base/axiosInstance';
import { LIKE_URL, POST_URL } from '../_base/urls';

export async function likeOrDislikePost(token, postId) {
  const URL = `${POST_URL}/${postId}${LIKE_URL}`;

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
