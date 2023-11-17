import './chatbot.style.css';

import sender from '../../../../assets/icons/send-white.svg';

import { UseHandleOpenAi } from '../../../../hooks/openAi/useHandleOpenAi';

import { BaseScreen, Message } from '../../../components';

export function Chatbot() {
  const { messages, newMessage, handleSend, onChange } = UseHandleOpenAi();

  return (
    <BaseScreen at={'chatbot'}>
      <section className='chatbot-container'>
        {messages
          ? messages.map((message, index) => (
              <Message
                key={index}
                message={message?.message}
                isMe={message?.isMe}
              />
            ))
          : null}
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
