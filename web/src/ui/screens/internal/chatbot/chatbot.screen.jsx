import './chatbot.style.css';

import sender from '../../../../assets/icons/send-white.svg';

import { UseHandleOpenAi } from '../../../../hooks/openAi/useHandleOpenAi';

import { BaseScreen, Message } from '../../../components';

export function Chatbot() {
  const { messages, newMessage, handleSend, onChange } = UseHandleOpenAi();

  return (
    <BaseScreen at={'chatbot'}>
      <section className='chatbot-container'>
        {messages?.map((message) => (
          <Message message={message?.message} isMe={message?.isMe} />
        ))}
        <div className='send-message'>
          <input type='text' onChange={onChange} value={newMessage} />
          <button onClick={handleSend}>
            <img src={sender} />
          </button>
        </div>
      </section>
    </BaseScreen>
  );
}
