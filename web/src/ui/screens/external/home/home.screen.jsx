import './home.style.css';

import { useNavigate } from 'react-router-dom';

import image from '../../../../assets/images/Home.png';
import arrow from '../../../../assets/icons/arrow-white.svg';

import { Card } from '../../../components';

export function Home() {
  const navigate = useNavigate();

  return (
    <>
      <section className='home'>
        <button className='home-button' onClick={() => navigate('/signup')}>
          <img src={arrow} alt='' className='mirror' />
          <p>Cadastro</p>
        </button>
        <img src={image} alt='' />
        <button className='home-button' onClick={() => navigate('/signin')}>
          <p>Login</p>
          <img src={arrow} alt='' />
        </button>
      </section>
      <section className='about-us'>
        <div className='card-holder'>
          <h1>
            Sobre <br />
            nós
          </h1>
          <Card />
        </div>
        <article className='banner'>
          <h1>
            O que é o <br />
            Allywatch?
          </h1>
          <p>
            O AllyWatch é uma plataforma que tem como objetivo servir como uma
            rede de apoio a vítimas de violência sexual e assédio moral dentro
            do ambiente escolar, oferecendo funcionalidades ao usuário que podem
            auxiliá-lo a identificar se o que está ocorrendo com ele é algo que
            merece atenção profissional, ajudá-lo a navegar por este momento
            ruim e procurar a ajuda e o apoio - psicológico, legal ou social -
            que desejar.
          </p>
        </article>
        <a href='#top'>
          <img src={arrow} alt='White arrow' className='go-up' />
        </a>
      </section>
    </>
  );
}
