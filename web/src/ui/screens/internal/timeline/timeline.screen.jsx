import './timeline.style.css';

import { useEffect } from 'react';

import switchArrow from '../../../../assets/icons/switch-arrow-pink.svg';
import circledPlus from '../../../../assets/icons/circled-plus.svg';
import whiteArrow from '../../../../assets/icons/short-arrow-white.svg';

import useGlobalReload from '../../../../context/reload/reload.context';

import { UseHandlePosts } from '../../../../hooks/posts/useHandlePosts.hook';

import { AddPost, BaseScreen, Post, RightTab } from '../../../components';

export function Timeline() {
  const [reload, setReload] = useGlobalReload();
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
    addPostIsOpen,
    closeAddPost,
  } = UseHandlePosts();

  useEffect(() => {
    getPosts();
  }, [reload]);

  return (
    <BaseScreen at='home' rightTab={true}>
      <section className='timeline-container'>
        {posts.map((post) => (
          <Post key={post?.id} post={post} />
        ))}
        <AddPost isOpen={addPostIsOpen} onClose={closeAddPost} />
      </section>
      <RightTab class={'timeline-right-tab'}>
        <button onClick={switchOrder} className='switch-order'>
          <img src={switchArrow} />
          <h3>
            {order === 'publicationTime' ? 'Mais recentes' : 'Mais curtidas'}
          </h3>
        </button>
        <button onClick={closeAddPost} className='switch-order'>
          <img src={circledPlus} />
          <h3>Fazer publicação</h3>
        </button>
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
