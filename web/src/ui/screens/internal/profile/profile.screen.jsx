import './profile.style.css';

import { useEffect, useState } from 'react';

import { avatarList } from '../../../../assets/arrays/avatars';
import whiteArrow from '../../../../assets/icons/short-arrow-white.svg';
import pencil from '../../../../assets/icons/pencil-white.svg';

import useGlobalReload from '../../../../context/reload/reload.context';

import { UseHandlePosts } from '../../../../hooks/posts/useHandlePosts.hook';

import { BaseScreen, Loader, Post, RightTab } from '../../../components';
import { UseHandleUser } from '../../../../hooks/user/useHandleUser.hook';
import useGlobalLoading from '../../../../context/load/loading.context';

export function Profile() {
  const [reload] = useGlobalReload();
  const [isLoading] = useGlobalLoading();
  const {
    posts,
    page,
    nextPage,
    previousPage,
    hasNextPage,
    hasPreviousPage,
    getMyPosts,
  } = UseHandlePosts();
  const { getInfo, user, deleteAccount, editOpen, editIcon, setEditOpen } =
    UseHandleUser();

  useEffect(() => {
    getMyPosts();
    getInfo();
  }, [reload, page]);

  return (
    <BaseScreen at={'profile'} rightTab={true}>
      <section className='timeline-container'>
        {isLoading ? (
          <Loader />
        ) : (
          posts?.map((post) => {
            return <Post post={post} />;
          })
        )}
      </section>
      <RightTab className={'profile'}>
        {isLoading ? (
          <Loader />
        ) : (
          <>
            <div className='profile-pic'>
              <img src={avatarList[user.icon]} />
            </div>
            <div className='edit-icon'>
              {editOpen ? (
                <>
                  <button
                    className='change-icon blue'
                    onClick={() => editIcon('MALE')}
                  ></button>
                  <button
                    className='change-icon pink'
                    onClick={() => editIcon('FEMALE')}
                  ></button>
                  <button
                    className='change-icon yellow'
                    onClick={() => editIcon('NEUTRAL')}
                  ></button>
                </>
              ) : (
                <img
                  src={pencil}
                  className='pencil'
                  onClick={() => setEditOpen(true)}
                />
              )}
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
          </>
        )}
      </RightTab>
    </BaseScreen>
  );
}
