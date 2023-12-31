import { axiosInstance } from '../_base/axiosInstance';
import { POST_URL } from '../_base/urls';

export async function listAllPosts(token, order, page) {
  const URL = `${POST_URL}?page=${page ? page : 0}&size=20&sort=${order},desc`;

  const response = await axiosInstance.get(URL, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
}
