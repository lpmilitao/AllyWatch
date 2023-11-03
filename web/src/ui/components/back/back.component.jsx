import './back.style.css';

import { useNavigate } from 'react-router-dom';

import pink from '../../../assets/icons/short-arrow-pink.svg';
import white from '../../../assets/icons/short-arrow-white.svg';

export function Back({ mode, route, side }) {
  const navigate = useNavigate();

  return (
    <div
      className={
        side === 'right' ? 'back-button right-side' : 'back-button left-side'
      }
      onClick={() => navigate(route ? route : -1)}
    >
      <img
        src={mode === 'white' ? white : pink}
        alt='Pink arrow'
        className={side === 'right' ? 'mirror' : ''}
      />
      <p>Voltar</p>
    </div>
  );
}
