import { axiosInstance } from '../_base/axiosInstance';
import { COMMENT_URL, POST_URL } from '../_base/urls';

export async function deleteComment(token, commentId) {
  const URL = `${POST_URL}${COMMENT_URL}/${commentId}`;

  const response = await axiosInstance.delete(URL, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
}
