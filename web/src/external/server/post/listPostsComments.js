import { axiosInstance } from '../_base/axiosInstance';
import { COMMENT_URL, POST_URL } from '../_base/urls';

export async function listPostsComments(token, postId) {
  const URL = `${POST_URL}/${postId}${COMMENT_URL}`;

  const response = await axiosInstance.get(URL, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
}
