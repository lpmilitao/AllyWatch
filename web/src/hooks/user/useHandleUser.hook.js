import { toast } from 'react-toastify';
import useGlobalReload from '../../context/reload/reload.context';
import useGlobalUser from '../../context/user/user.context';
import { getUserInfo } from '../../external/server/user/getUserInfo';
import { useState } from 'react';
import { deleteUser } from '../../external/server';
import { Navigate, useNavigate } from 'react-router-dom';

export function UseHandleUser() {
  const [token] = useGlobalUser();
  const [reload, setReload] = useGlobalReload();
  const [user, setUser] = useState({
    nome: '',
    email: '',
    icon: 'NEUTRAL',
  });
  const navigate = useNavigate();

  async function getInfo() {
    try {
      const response = await getUserInfo(token);
      setUser(response);
    } catch (error) {
      toast.error('Erro ao carregar informações do usuário', {
        position: 'top-right',
      });
    }
  }

  async function deleteAccount() {
    try {
      await deleteUser(token);

      localStorage.removeItem('user');
      navigate('/home');
    } catch (error) {
      toast.error('Erro ao deletar conta', {
        position: 'top-right',
      });
    }
  }

  return {
    getInfo,
    user,
    deleteAccount,
  };
}
