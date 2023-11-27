import './checkbox.style.css';

export function Checkbox({ name, onChange }) {
  return (
    <div class='check'>
      <input id='check' type='checkbox' name={name} onChange={onChange} />
      <label for='check'></label>
    </div>
  );
}
