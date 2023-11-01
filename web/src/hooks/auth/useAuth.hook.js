import { useState } from 'react';

import { login } from '../../external/server';

import useGlobalUser from '../../context/user/user.context';
import { useNavigate } from 'react-router-dom';

export function useAuth() {
  const [user, setUser] = useState({
    email: '',
    password: '',
  });
  const [, setGlobalUser] = useGlobalUser();
  const navigate = useNavigate();

  async function handleLogin(event) {
    event.preventDefault();

    try {
      const response = await login(user);
      setGlobalUser(response);

      navigate('/');
    } catch (error) {
      console.log(error);
    }
  }

  function onChange(event) {
    setUser({
      ...user,
      [event.target.name]: event.target.value,
    });
  }

  return {
    user,
    handleLogin,
    onChange,
  };
}
