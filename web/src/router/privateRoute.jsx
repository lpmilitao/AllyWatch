import { Navigate } from 'react-router-dom';
//import useGlobalUsuario from '../context/usuario/usuario.context';

const URL_LOGIN = '/signin';

export function PrivateRoute({ Screen }) {
  //const [usuario] = useGlobalUsuario();
  const usuario = {};
  if (usuario) {
    return <Screen />;
  }

  return <Navigate to={URL_LOGIN} />;
}
