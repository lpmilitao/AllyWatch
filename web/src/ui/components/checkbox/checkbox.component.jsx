import './checkbox.style.css';

export function Checkbox({ name, onChange, checked }) {
  return (
    <div class='check'>
      <input
        id='check'
        type='checkbox'
        name={name}
        onChange={onChange}
        checked={checked}
      />
      <label for='check'></label>
    </div>
  );
}
