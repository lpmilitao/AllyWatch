import './sendButton.style.css';

export function SendButton({ onClick, text }) {
  return (
    <button className='send-button' type='submit' onClick={onClick}>
      <p>{text}</p>
    </button>
  );
}
