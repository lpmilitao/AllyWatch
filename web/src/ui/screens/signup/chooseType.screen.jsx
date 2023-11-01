import './chooseType.style.css';

import { useNavigate } from 'react-router-dom';

import specialist from '../../../assets/icons/specialist.svg';
import help from '../../../assets/icons/help.svg';

import { Back } from '../../components';

export function ChooseType() {
  const navigate = useNavigate();

  return (
    <>
      <Back mode={'white'} />
      <section className='choose-type'>
        <h1>Cadastre-se como</h1>
        <div>
          <div
            className='select-card'
            onClick={() => navigate('signup-specialist')}
          >
            <h2>Especialista</h2>
            <img
              src={specialist}
              alt='Ícone de uma homem branco de cabelo rosa falando ao telefone'
            />
            <p>
              Cadastre-se como um especialista e ofereça seus serviços no
              AllyWatch.
            </p>
          </div>
          <div className='select-card' onClick={() => navigate('signup-user')}>
            <h2>Usuário</h2>
            <img
              src={help}
              alt='ícone de uma mão branca segurando um coração rosa, simbolizando ajuda'
            />
            <p>
              Cadastre-se como usuário e faça parte da construção desta rede de
              apoio.
            </p>
          </div>
        </div>
      </section>
    </>
  );
}
