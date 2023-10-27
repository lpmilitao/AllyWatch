import { axiosInstance } from "../_base/axiosInstance";
import { USER_URL } from "../_base/urls";

export async function createNewUser({fullname, cpf, email, password}) {
    
    const response = await axiosInstance.post(
        USER_URL,
        {
            fullname,
            cpf,
            email,            
            password
        },
      );
      return response.data;
}