import './radio.style.css';

export function Radio({ onChange, value, title, name }) {
  return (
    <div className='radio'>
      <input
        id={value}
        type='radio'
        name={name}
        value={value}
        onChange={onChange}
      />
      <label htmlFor={value}>{title}</label>
    </div>
  );
}
