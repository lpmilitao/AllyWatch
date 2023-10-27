import { axiosInstance } from "../_base/axiosInstance";
import { USER_URL } from "../_base/urls";

export async function deleteUser(token) {
    
    const response = await axiosInstance.delete(
        USER_URL,
        {},
        {
            headers: {
              'Authorization': `Bearer ${token}`
            }
          }
      );
      return response.data;
}