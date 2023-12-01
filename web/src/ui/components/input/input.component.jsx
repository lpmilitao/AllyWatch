import './input.style.css';

import { useState } from 'react';

import eye from '../../../assets/icons/visible-eye.png';
import closedEye from '../../../assets/icons/invisible-eye.png';

export function Input({ type, onChange, value, title, name }) {
  const [eyeOpen, setEyeOpen] = useState(false);
  const [typeInput, setTypeInput] = useState(type);
  const [icon, setIcon] = useState(closedEye);

  function changeVisibility() {
    setEyeOpen(!eyeOpen);

    if (eyeOpen) {
      setTypeInput('text');
      setIcon(eye);
    } else {
      setTypeInput('password');
      setIcon(closedEye);
    }
  }

  return (
    <div className='input'>
      <h3>{title}</h3>
      <input type={typeInput} onChange={onChange} value={value} name={name} />
      {type === 'password' ? (
        <img src={icon} className='eye' onClick={changeVisibility} />
      ) : null}
    </div>
  );
}
