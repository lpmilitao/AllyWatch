import './home.style.css';

import { useNavigate } from 'react-router-dom';
import { useState } from 'react';

import image from '../../../assets/images/Home.png';
import arrow from '../../../assets/icons/arrow-white.svg';

import { Card } from '../../components';

export function Home() {
  const navigate = useNavigate();
  const [index, setIndex] = useState(0);

  function next() {
    if (index + 1 > 6) {
      setIndex(0);
    } else {
      setIndex(index + 1);
    }
  }

  function previous() {
    if (index - 1 < 0) {
      setIndex(6);
    } else {
      setIndex(index - 1);
    }
  }

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
        <div>
          <h1>
            Sobre <br />
            nós
          </h1>
          <Card index={index} previous={previous} next={next} />
        </div>
        <article className='banner'>
          <h1>
            O que é o <br />
            Allywatch?
          </h1>
          <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Sapiente
            eius fuga ut temporibus reiciendis facilis autem beatae laborum
            obcaecati assumenda, dignissimos ducimus sequi error aperiam enim
            culpa deserunt fugiat esse! Lorem ipsum dolor sit amet consectetur,
            adipisicing elit. Ad molestias corrupti laborum, natus doloremque.
          </p>
        </article>
      </section>
    </>
  );
}
