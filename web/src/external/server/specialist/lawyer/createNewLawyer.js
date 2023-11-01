import { axiosInstance } from '../../_base/axiosInstance';
import { LAWYER_URL, SPECIALIST_URL } from '../../_base/urls';

export async function createNewLawyer({
  fullname,
  state,
  oabRegisterNumber,
  email,
  phone,
  city,
}) {
  const URL = `${SPECIALIST_URL}${LAWYER_URL}`;

  const response = await axiosInstance.post(URL, {
    fullname,
    state,
    oabRegisterNumber,
    email,
    phone,
    city,
  });
  return response.data;
}
