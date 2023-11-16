import './message.style.css';

export function Message({ message, isMe }) {
  return (
    <div className={`message ${isMe ? 'me' : 'friend'}`}>
      <p className='message'>{message}</p>
    </div>
  );
}
