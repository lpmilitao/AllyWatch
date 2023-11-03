import { useState } from 'react';
import { createNewUser } from '../../../external/server';

import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.min.css';

export function UseRegisterUser() {
  const [user, setUser] = useState({
    fullname: '',
    cpf: '',
    email: '',
    password: '',
    passwordConfirmation: '',
  });

  function onChange(event) {
    setUser({
      ...user,
      [event.target.name]: event.target.value,
    });
  }

  async function handleRegister(event) {
    event.preventDefault();

    if (!verifyPassword()) {
      toast.error(
        'A senha deve conter pelo menos: 8 caracteres, 1 letra maiúscula, 1 letra minúscula, ' +
          '1 número e 1 caractere especial.',
        {
          position: 'bottom-left',
        },
      );
      return;
    }

    if (user.password !== user.passwordConfirmation) {
      toast.error('As senhas não coincidem.', {
        position: 'bottom-left',
      });
      return;
    }

    trimCPF();

    try {
      await createNewUser(user);
      toast.success('Cadastro realizado com sucesso! Agora, faça seu login.', {
        position: 'bottom-left',
      });
    } catch (error) {
      toast.error('Erro ao cadastrar usuário.', {
        position: 'bottom-left',
      });
    }
  }

  function verifyPassword() {
    const size = /.{8,}/;
    const upperCase = /[A-Z]/;
    const lowerCase = /[a-z]/;
    const number = /[0-9]/;
    const special = /[\!\@\#\$\%\^\&\*\(\)\-\+\=]/;

    return (
      size.test(user.password) &&
      upperCase.test(user.password) &&
      lowerCase.test(user.password) &&
      number.test(user.password) &&
      special.test(user.password)
    );
  }

  function trimCPF() {
    setUser({
      ...user,
      cpf: user.cpf.replace(/[^\d]/g, ''),
    });
  }

  return {
    user,
    onChange,
    handleRegister,
  };
}
