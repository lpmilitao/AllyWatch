import { useState } from 'react';

import { login, logout } from '../../external/server';

import useGlobalUser from '../../context/user/user.context';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

export function UseAuth() {
  const [user, setUser] = useState({
    email: '',
    password: '',
  });
  const [globalUser, setGlobalUser] = useGlobalUser();
  const navigate = useNavigate();

  async function handleLogin(event) {
    event.preventDefault();

    try {
      const response = await login(user);
      setGlobalUser(response);

      navigate('/');
    } catch (error) {
      toast.error('Erro ao fazer login.', {
        position: 'top-right',
      });
    }
  }

  function onChange(event) {
    setUser({
      ...user,
      [event.target.name]: event.target.value,
    });
  }

  async function handleLogout() {
    try {
      await logout(globalUser);
      setGlobalUser(null);
      navigate('/home');
    } catch (error) {
      toast.error('Erro ao fazer logout.', {
        position: 'top-right',
      });
    }
  }

  return {
    user,
    handleLogin,
    onChange,
    handleLogout,
  };
}
