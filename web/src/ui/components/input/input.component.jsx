import './input.style.css';

export function Input({ type, onChange, value, title, name }) {
  return (
    <div className='input'>
      <h3>{title}</h3>
      <input type={type} onChange={onChange} value={value} name={name} />
    </div>
  );
}
