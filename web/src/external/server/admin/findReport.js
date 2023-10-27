import { axiosInstance } from '../_base/axiosInstance';
import { ADMIN_URL, REPORT_URL } from '../_base/urls';

export async function findReport(token, reportId) {
  const URL = `${ADMIN_URL}${REPORT_URL}/${reportId}`;

  const response = await axiosInstance.get(
    URL,
    {},
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    },
  );
  return response.data;
}
