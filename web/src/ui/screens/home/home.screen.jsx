import './home.style.css';

import image from '../../../assets/images/Home.png';
import arrow from '../../../assets/icons/arrow-white.svg';
import { useNavigate } from 'react-router-dom';

export function Home() {
  const navigate = useNavigate();

  return (
    <section className='home'>
      <button className='home-button' onClick={() => navigate('/signup')}>
        <img src={arrow} alt='' className='left' />
        <p>Cadastro</p>
      </button>
      <img src={image} alt='' />
      <button className='home-button' onClick={() => navigate('/signin')}>
        <p>Login</p>
        <img src={arrow} alt='' />
      </button>
    </section>
  );
}
