import { Navigate } from 'react-router-dom';
import useGlobalUser from '../context/user/user.context';
import { isTokenExpired } from './tokenExpiration';

export function PrivateRoute({ Screen }) {
  const [user] = useGlobalUser();

  if (user && isTokenExpired(user)) {
    return <Screen />;
  }

  return <Navigate to={'/home'} />;
}
