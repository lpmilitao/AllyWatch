import './signin.style.css';

import { Link } from 'react-router-dom';

import logo from '../../../assets/images/logo.png';

import { Back, Input, SendButton } from '../../components';

export function SignIn() {
  return (
    <section className='sign-in-container'>
      <Back />
      <div className='login-modal'>
        <img src={logo} alt='Logo do AllyWatch' />
        <h1>Login</h1>
        <Input type='text' name='E-mail' />
        <Input type='text' name='Senha' />
        <SendButton onClick={null} text={'Entrar'} />
        <div className='redirect'>
          <Link to=''>esqueci minha senha </Link>
          <Link to='/signup'> não tenho uma conta</Link>
        </div>
      </div>
    </section>
  );
}
