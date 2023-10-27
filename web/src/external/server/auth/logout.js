import { axiosInstance } from "../_base/axiosInstance";
import { LOGOUT_URL } from "../_base/urls";

export async function logout(token) {
    
    const response = await axiosInstance.post(
        LOGOUT_URL,
        {},
        {
            headers: {
              'Authorization': `Bearer ${token}`
            }
          }
      );
      return response.data;
}