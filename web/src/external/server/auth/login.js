import { axiosInstance } from "../_base/axiosInstance";
import { LOGIN_URL } from "../_base/urls";

export async function login({ email, password }) {
  const response = await axiosInstance.post(
    LOGIN_URL,
    {
        email,
        password
    }
  );
  return response.data;
}