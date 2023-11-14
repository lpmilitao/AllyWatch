import { axiosInstance } from '../_base/axiosInstance';
import { POST_URL } from '../_base/urls';

export async function getPostDetails(token, postId) {
  const URL = `${POST_URL}/${postId}`;

  const response = await axiosInstance.get(URL, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
}
