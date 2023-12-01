import './chats.style.css';

import { useEffect } from 'react';

import send from '../../../../assets/icons/send-pink.svg';
import { avatarList } from '../../../../assets/arrays/avatars';
import background from '../../../../assets/images/chat.png';

import useGlobalReload from '../../../../context/reload/reload.context';

import { UseHandleChats } from '../../../../hooks';

import { BaseScreen, Loader, Message, RightTab } from '../../../components';
import useGlobalLoading from '../../../../context/load/loading.context';

export function Chats() {
  const [reload] = useGlobalReload();
  const [isLoading] = useGlobalLoading();
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

  function handleKeyPress(e) {
    if (e.key === 'Enter') {
      handleNewMessage();
    }
  }

  return (
    <BaseScreen at={'chat'} rightTab={true}>
      {isLoading ? (
        <Loader />
      ) : (
        <section className='chat-container'>
          {isChatSelected ? (
            <>
              <span>
                Essa conversa foi iniciada pelo AllyWatch. Isso significa que
                você e esta pessoa foram, possivelmente, vítimas de um mesmo
                agressor, e por isso, colocamos vocês em contato para que tomem
                alguma provindência em conjunto, troquem relatos e ajudem-se. Se
                este chat está aberto, significa que vocês dois aceitaram
                participar dele.
              </span>
              {chat?.messages?.map((message) => {
                return (
                  <Message
                    message={message.message}
                    isMe={message.sentByMe}
                    key={message.id}
                  />
                );
              })}

              <div className='sender'>
                <input
                  type='text'
                  value={newMessage}
                  onChange={onChange}
                  onKeyPress={handleKeyPress}
                />
                <div onClick={handleNewMessage}>
                  <img src={send} />
                </div>
              </div>
            </>
          ) : (
            <>
              <span className='chat-title'>
                Os chats são um local onde você pode conversar com pessoas que o
                AllyWatch te conecta. Caso você denuncie um agressor através de
                um post e uma outra pessoa denuncie esse mesmo agressor, a
                plataforma enviará uma solicitação de participação de chat para
                ambos.
                <br />
                Caso os dois aceitem, será possível que conversem através de um
                chat.
              </span>
              <img src={background} className='background-chat' />
            </>
          )}
        </section>
      )}
      <RightTab>
        <div className='switch-list'>
          <button
            onClick={() => setListOpened('chats')}
            className={`${listOpened === 'chats' ? 'active' : null}`}
          >
            Chats
          </button>
          <button
            onClick={() => setListOpened('solicitations')}
            className={`${listOpened === 'solicitations' ? 'active' : null}`}
          >
            Solicitações
          </button>
        </div>
        {!isLoading ? (
          <>
            {listOpened === 'chats'
              ? chats.map((chat) => {
                  return (
                    <>
                      <div
                        className={`chat ${
                          chat.id === chatSelectedId ? 'active' : ''
                        }`}
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
                    </>
                  );
                })
              : solicitatons?.map((solicitation) => {
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
          </>
        ) : null}
      </RightTab>
    </BaseScreen>
  );
}
