import { toast } from 'react-toastify';
import { jwtDecode } from 'jwt-decode';

export function isTokenExpired(token) {
  let decoded = {};

  try {
    decoded = jwtDecode(token);
  } catch (error) {
    toast.error('Token inválido', {
      position: toast.POSITION.TOP_RIGHT,
    });
  }

  const now = Date.now().valueOf() / 1000;

  if (typeof decoded.exp !== 'undefined' && decoded.exp < now) {
    toast.warn('Sessão expirada, faça login novamente.', {
      position: toast.POSITION.TOP_RIGHT,
    });
    return false;
  }
  return true;
}
