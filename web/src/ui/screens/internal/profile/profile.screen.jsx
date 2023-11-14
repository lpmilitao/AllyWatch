import './profile.style.css';

import { useEffect, useState } from 'react';
import { toast } from 'react-toastify';

import { avatarList } from '../../../../assets/arrays/avatars';
import whiteArrow from '../../../../assets/icons/short-arrow-white.svg';

import useGlobalReload from '../../../../context/reload/reload.context';

import { UseHandlePosts } from '../../../../hooks/posts/useHandlePosts.hook';

import { BaseScreen, Post, RightTab } from '../../../components';
import { UseHandleUser } from '../../../../hooks/user/useHandleUser.hook';

export function Profile() {
  const [reload] = useGlobalReload();
  const {
    posts,
    page,
    nextPage,
    previousPage,
    hasNextPage,
    hasPreviousPage,
    getMyPosts,
  } = UseHandlePosts();
  const { getInfo, user, deleteAccount } = UseHandleUser();

  useEffect(() => {
    getMyPosts();
    getInfo();
  }, [reload, page]);

  return (
    <BaseScreen at={'profile'} rightTab={true}>
      <section className='timeline-container'>
        {posts?.map((post) => {
          return <Post post={post} />;
        })}
      </section>
      <RightTab className={'profile'}>
        <div className='profile-pic'>
          <img src={avatarList[user.icon]} />
        </div>
        <h1>{user.name}</h1>
        <h3>{user.email}</h3>
        <span onClick={deleteAccount}>Excluir minha conta</span>
        <div className='pagination-holder'>
          <img
            src={whiteArrow}
            onClick={hasPreviousPage ? previousPage : null}
          />
          <h3>{page + 1}</h3>
          <img
            src={whiteArrow}
            className='mirror'
            onClick={hasNextPage ? nextPage : null}
          />
        </div>
      </RightTab>
    </BaseScreen>
  );
}
