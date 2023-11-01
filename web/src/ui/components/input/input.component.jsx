import './input.style.css';

export function Input({ type, onChange, value, name }) {
  return (
    <div className='input'>
      <h3>{name}</h3>
      <input type={type} onChange={onChange} value={value} />
    </div>
  );
}
