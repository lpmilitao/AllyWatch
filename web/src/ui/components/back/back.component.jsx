import './back.style.css';

import { useNavigate } from 'react-router-dom';

import arrow from '../../../assets/icons/short-arrow-pink.svg';

export function Back() {
  const navigate = useNavigate();

  return (
    <div className='back-button' onClick={() => navigate(-1)}>
      <img src={arrow} alt='Pink arrow' />
      <p>Voltar</p>
    </div>
  );
}
