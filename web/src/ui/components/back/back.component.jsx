import './back.style.css';

import { useNavigate } from 'react-router-dom';

import pink from '../../../assets/icons/short-arrow-pink.svg';
import white from '../../../assets/icons/short-arrow-white.svg';

export function Back({ mode, route }) {
  const navigate = useNavigate();

  return (
    <div className='back-button' onClick={() => navigate(route ? route : -1)}>
      <img src={mode === 'white' ? white : pink} alt='Pink arrow' />
      <p>Voltar</p>
    </div>
  );
}
