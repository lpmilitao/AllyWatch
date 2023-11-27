import { toast } from 'react-toastify';
import useGlobalReload from '../../context/reload/reload.context';
import useGlobalUser from '../../context/user/user.context';
import { getUserInfo } from '../../external/server/user/getUserInfo';
import { useState } from 'react';
import { deleteUser, editUsersIcon } from '../../external/server';
import { Navigate, useNavigate } from 'react-router-dom';
import useGlobalLoading from '../../context/load/loading.context';

export function UseHandleUser() {
  const [token] = useGlobalUser();
  const [reload, setReload] = useGlobalReload();
  const [, setLoading] = useGlobalLoading();

  const [user, setUser] = useState({
    nome: '',
    email: '',
    icon: 'NEUTRAL',
  });
  const [editOpen, setEditOpen] = useState(false);
  const navigate = useNavigate();

  async function getInfo() {
    setLoading(true);
    try {
      const response = await getUserInfo(token);
      setUser(response);
    } catch (error) {
      toast.error('Erro ao carregar informações do usuário', {
        position: 'top-right',
      });
    } finally {
      setLoading(false);
    }
  }

  async function deleteAccount() {
    setLoading(true);
    try {
      await deleteUser(token);

      localStorage.removeItem('user');
      navigate('/home');
    } catch (error) {
      toast.error('Erro ao deletar conta', {
        position: 'top-right',
      });
    } finally {
      setLoading(false);
    }
  }

  async function editIcon(icon) {
    if (icon === user.icon) {
      setEditOpen(false);
      return;
    }

    setLoading(true);

    try {
      await editUsersIcon(token, icon);
      toast.success('Icone alterado com sucesso.', {
        position: 'bottom-right',
      });
      setReload(!reload);
      setEditOpen(false);
    } catch (error) {
      toast.error('Erro ao alterar icone.', {
        position: 'top-right',
      });
    } finally {
      setLoading(false);
    }
  }

  return {
    getInfo,
    user,
    deleteAccount,
    editOpen,
    editIcon,
    setEditOpen,
  };
}
