import './signin.style.css';

import { Link } from 'react-router-dom';

import logo from '../../../assets/images/logo.png';

import { Back, Input, SendButton } from '../../components';
import { useAuth } from '../../../hooks';

export function SignIn() {
  const { user, handleLogin, onChange } = useAuth();

  return (
    <section className='sign-in-container'>
      <Back route={'/home'} />
      <div className='login-modal'>
        <img src={logo} alt='Logo do AllyWatch' />
        <h1>Login</h1>
        <Input
          type={'text'}
          title={'E-mail'}
          name={'email'}
          value={user.email}
          onChange={onChange}
        />
        <Input
          type={'text'}
          title={'Senha'}
          name={'password'}
          value={user.password}
          onChange={onChange}
        />
        <SendButton onClick={handleLogin} text={'Entrar'} />
        <div className='redirect'>
          <Link to=''>esqueci minha senha </Link>
          <Link to='/signup'> não tenho uma conta</Link>
        </div>
      </div>
    </section>
  );
}
