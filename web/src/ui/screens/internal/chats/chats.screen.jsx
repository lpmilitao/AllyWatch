import './chats.style.css';

import { avatarList } from '../../../../assets/arrays/avatars';

import { UseHandleChats } from '../../../../hooks';

import { BaseScreen, RightTab } from '../../../components';
import { useEffect } from 'react';

const CHATS = [
  {
    id: 1,
    open: true,
    ally: 'Joana Joana Joana Joana Joana Joana',
    allyIcon: 'NEUTRAL',
  },
  { id: 2, open: true, ally: 'Maria', allyIcon: 'FEMALE' },
  { id: 3, open: true, ally: 'Paulo', allyIcon: 'MALE' },
  { id: 4, open: true, ally: 'Catarina', allyIcon: 'NEUTRAL' },
];

const SOLICITATIONS = [
  {
    id: 1,
    status: 'UNDER_REVIEW',
    requestedUser: 'Luiza',
    requestingUser: 'Carla Carla Carla Carla Carla',
    requestingUserIcon: 'NEUTRAL',
  },
  {
    id: 2,
    status: 'UNDER_REVIEW',
    requestedUser: 'Luiza',
    requestingUser: 'Marcos',
    requestingUserIcon: 'MALE',
  },
  {
    id: 3,
    status: 'UNDER_REVIEW',
    requestedUser: 'Luiza',
    requestingUser: 'Paulo',
    requestingUserIcon: 'MALE',
  },
  {
    id: 4,
    status: 'UNDER_REVIEW',
    requestedUser: 'Luiza',
    requestingUser: 'Cristina',
    requestingUserIcon: 'FEMALE',
  },
];

export function Chats() {
  const {
    listOpened,
    setListOpened,
    chats,
    solicitatons,
    handleListChats,
    handleListSolicitations,
  } = UseHandleChats();

  useEffect(() => {
    //listOpened === 'chats' ? handleListChats() : handleListSolicitations();
  }, [listOpened]);

  return (
    <BaseScreen at={'chat'} rightTab={true}>
      <RightTab>
        <div className='switch-list'>
          <button onClick={() => setListOpened('chats')}>Chats</button>
          <button onClick={() => setListOpened('solicitations')}>
            Solicitações
          </button>
        </div>
        {listOpened === 'chats'
          ? CHATS.map((chat) => {
              return (
                <div className='chat'>
                  <img
                    src={avatarList[chat.allyIcon]}
                    className='chat-profile-pic'
                  />
                  <p>{chat.ally}</p>
                </div>
              );
            })
          : SOLICITATIONS.map((solicitation) => {
              return (
                <div className='solicitation'>
                  <img
                    src={avatarList[solicitation.requestingUserIcon]}
                    className='chat-profile-pic'
                  />
                  <p>{solicitation.requestingUser}</p>
                  <button className='right-side'>Aceitar</button>
                  <button>Recusar</button>
                </div>
              );
            })}
      </RightTab>
    </BaseScreen>
  );
}
