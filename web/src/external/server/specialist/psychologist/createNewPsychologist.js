import { PSYCHOLOGIST_URL, SPECIALIST_URL } from '../../_base/urls';
import { axiosInstance } from '../../_base/axiosInstance';

export async function createNewPsychology({
  fullname,
  state,
  registerNumber,
  email,
  phone,
  city,
  type,
  cpfOrCpnj,
}) {
  const URL = `${SPECIALIST_URL}${PSYCHOLOGIST_URL}`;

  const response = await axiosInstance.post(URL, {
    fullname,
    state,
    registerNumber,
    email,
    phone,
    city,
    type,
    cpfOrCpnj,
  });
  return response.data;
}
