import { axiosInstance } from '../_base/axiosInstance';
import { USER_URL } from '../_base/urls';

export async function getUserInfo(token) {
  const response = await axiosInstance.get(USER_URL, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
}
