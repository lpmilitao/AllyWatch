import './chats.style.css';

import { useEffect, useState } from 'react';

import send from '../../../../assets/icons/send-pink.svg';
import { avatarList } from '../../../../assets/arrays/avatars';

import useGlobalReload from '../../../../context/reload/reload.context';

import { UseHandleChats } from '../../../../hooks';

import { BaseScreen, Message, RightTab } from '../../../components';

export function Chats() {
  const [reload] = useGlobalReload();
  const {
    listOpened,
    setListOpened,
    chats,
    solicitatons,
    handleListChats,
    handleListSolicitations,
    acceptSolicitation,
    denySolicitation,
    chat,
    isChatSelected,
    setIsChatSelected,
    handleChatSelection,
    chatSelectedId,
    setChatSelectedId,
    newMessage,
    onChange,
    handleNewMessage,
  } = UseHandleChats();

  useEffect(() => {
    listOpened === 'chats' ? handleListChats() : handleListSolicitations();
  }, [listOpened, reload]);

  useEffect(() => {
    if (isChatSelected) {
      handleChatSelection();
    }
  }, [isChatSelected, chatSelectedId, reload]);

  return (
    <BaseScreen at={'chat'} rightTab={true}>
      <section className='chat-container'>
        <span>
          Essa conversa foi iniciada pelo AllyWatch. Isso significa que você e
          esta pessoa foram, possivelmente, vítimas de um mesmo agressor, e por
          isso, colocamos vocês em contato para que tomem alguma provindência em
          conjunto, troquem relatos e ajudem-se. Se este chat está aberto,
          significa que vocês dois aceitaram participar dele.
        </span>
        {isChatSelected
          ? chat?.messages?.map((message) => {
              return (
                <Message
                  message={message.message}
                  isMe={message.sentByMe}
                  key={message.id}
                />
              );
            })
          : null}
        <div className='sender'>
          <input type='text' value={newMessage} onChange={onChange} />
          <div onClick={handleNewMessage}>
            <img src={send} />
          </div>
        </div>
      </section>
      <RightTab>
        <div className='switch-list'>
          <button onClick={() => setListOpened('chats')}>Chats</button>
          <button onClick={() => setListOpened('solicitations')}>
            Solicitações
          </button>
        </div>
        {listOpened === 'chats'
          ? chats.map((chat) => {
              return (
                <div
                  className='chat'
                  onClick={() => {
                    setIsChatSelected(true);
                    setChatSelectedId(chat.id);
                  }}
                >
                  <img
                    src={avatarList[chat.allyIcon]}
                    className='chat-profile-pic'
                  />
                  <p>{chat.ally}</p>
                </div>
              );
            })
          : solicitatons.map((solicitation) => {
              return (
                <div className='solicitation'>
                  <img
                    src={avatarList[solicitation.requestingUserIcon]}
                    className='chat-profile-pic'
                  />
                  <p>{solicitation.requestingUser}</p>
                  <button
                    className='right-side'
                    onClick={() => acceptSolicitation(solicitation.id)}
                  >
                    Aceitar
                  </button>
                  <button onClick={() => denySolicitation(solicitation.id)}>
                    Recusar
                  </button>
                </div>
              );
            })}
      </RightTab>
    </BaseScreen>
  );
}
