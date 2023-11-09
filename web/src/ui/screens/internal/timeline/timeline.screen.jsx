import './timeline.style.css';

import { useEffect, useState } from 'react';

import useGlobalUser from '../../../../context/user/user.context';

import { UseGetPosts } from '../../../../hooks/posts/useGetPosts.hook';

import { BaseScreen, Post, RightTab } from '../../../components';

import switchArrow from '../../../../assets/icons/switch-arrow-pink.svg';
import circledPlus from '../../../../assets/icons/circled-plus.svg';
import whiteArrow from '../../../../assets/icons/short-arrow-white.svg';

const POST = {
  id: 1,
  title: 'Post title',
  body: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Quaerat sit voluptas assumenda, aliquid dolores ex optio enim officia animi veniam doloremque autem aut nobis accusantium sint distinctio eligendi culpa accusamus. Lorem, ipsum dolor sit amet consectetur adipisicing elit. Quaerat sit voluptas assumenda, aliquid dolores ex optio enim officia animi veniam doloremque autem aut nobis accusantium sint distinctio eligendi culpa accusamus.',
  publicatedAt: '2021-01-01 00:00:00',
  coments: [],
  numberOfLikes: 7,
  mine: false,
  author: 'Luiza',
  icon: 'FEMALE',
  anonymous: false,
  likedByMe: false,
};

const POSTS = [POST, POST, POST, POST, POST, POST, POST, POST, POST, POST];

export function Timeline() {
  const {
    posts,
    getPosts,
    order,
    switchOrder,
    page,
    nextPage,
    previousPage,
    hasNextPage,
    hasPreviousPage,
  } = UseGetPosts();

  useEffect(() => {
    getPosts();
  }, [order, page]);

  return (
    <BaseScreen at='home' rightTab={true}>
      <section className='timeline-container'>
        {posts.map((post) => (
          <Post key={post?.id} post={post} />
        ))}
      </section>
      <RightTab class={'timeline-right-tab'}>
        <button onClick={switchOrder} className='switch-order'>
          <img src={switchArrow} />
          <h3>
            {order === 'publicationTime' ? 'Mais recentes' : 'Mais curtidas'}
          </h3>
        </button>
        <button onClick={switchOrder} className='switch-order'>
          <img src={circledPlus} />
          <h3>Fazer publicação</h3>
        </button>
        <div className='pagination-holder'>
          <img src={whiteArrow} onClick={previousPage} />
          <h3>{page + 1}</h3>
          <img src={whiteArrow} className='mirror' onClick={nextPage} />
        </div>
      </RightTab>
    </BaseScreen>
  );
}
