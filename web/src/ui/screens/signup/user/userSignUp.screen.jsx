import './userSignUp.style.css';

import { Link } from 'react-router-dom';

import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.min.css';

import help from '../../../../assets/images/help.png';

import { UseRegisterUser } from '../../../../hooks';

import { Back, Form, Input, SendButton } from '../../../components';

export function UserSignUp() {
  const { user, onChange, handleRegister } = UseRegisterUser();

  return (
    <>
      <Back route={'/signup'} />
      <section className='user-signup-container'>
        <ToastContainer />

        <div className='presentation'>
          <div>
            <h1>Bem-vindo ao AllyWatch</h1>
            <p>
              Junte-se a nós para criar um ambiente escolar seguro e acolhedor.
              Cadastre-se agora e faça parte dessa rede de apoio. <br />
              Juntos, podemos fazer a diferença!
            </p>
          </div>
          <img src={help} alt='' />
        </div>

        <Form>
          <h1>Junte-se ao AllyWatch</h1>
          <Input
            type='text'
            title={'CPF'}
            value={user.cpf}
            onChange={onChange}
            name={'cpf'}
          />
          <Input
            type='text'
            title={'Nome completo'}
            value={user.fullname}
            onChange={onChange}
            name={'fullname'}
          />
          <Input
            type='text'
            title={'E-mail'}
            value={user.email}
            onChange={onChange}
            name={'email'}
          />
          <Input
            type='password'
            title={'Senha'}
            value={user.password}
            onChange={onChange}
            name={'password'}
          />
          <Input
            type='password'
            title={'Confirmação de senha'}
            value={user.passwordConfirmation}
            onChange={onChange}
            name={'passwordConfirmation'}
          />
          <SendButton text={'Cadastre-se'} onClick={handleRegister} />
          <Link to={'/signin'}>já tenho uma conta</Link>
        </Form>
      </section>
    </>
  );
}
