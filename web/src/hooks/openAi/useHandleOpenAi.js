import { useState } from 'react';
import { toast } from 'react-toastify';

import { sendMessage } from '../../external/openAi/sendMessage';

export function UseHandleOpenAi() {
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');

  async function handleSend() {
    try {
      setMessages((currentMessages) => [
        ...currentMessages,
        { message: newMessage, isMe: true },
      ]);
      setNewMessage('');

      const response = await sendMessage(
        messages.map((m) => {
          return {
            role: m.isMe ? 'user' : 'system',
            content: m.message,
          };
        }),
      );

      setMessages((currentMessages) => [
        ...currentMessages,
        { message: response, isMe: false },
      ]);
    } catch (error) {
      toast.error('Erro ao enviar a mensagem.', {
        position: toast.POSITION.TOP_RIGHT,
      });
    }
  }

  function onChange(event) {
    setNewMessage(event.target.value);
  }

  return {
    messages,
    newMessage,
    handleSend,
    onChange,
  };
}
