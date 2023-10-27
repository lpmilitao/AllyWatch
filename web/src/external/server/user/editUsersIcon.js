import { axiosInstance } from "../_base/axiosInstance";
import { USER_URL } from "../_base/urls";

export async function editUsersIcon(token, icon) {
    
    const response = await axiosInstance.put(
        USER_URL,
        {
            'icon': icon
        },
        {
            headers: {
              'Authorization': `Bearer ${token}`
            }
          }
      );
      return response.data;
}