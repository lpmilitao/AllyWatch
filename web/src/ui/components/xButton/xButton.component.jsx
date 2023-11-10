import './xButton.style.css';

import x from '../../../assets/icons/x-pink.svg';

export function XButton({ onClick }) {
  return (
    <button className='x-button'>
      <img src={x} onClick={onClick} />
    </button>
  );
}
