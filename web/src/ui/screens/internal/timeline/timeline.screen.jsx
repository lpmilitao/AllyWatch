import './timeline.style.css';

import { useEffect } from 'react';

import switchArrow from '../../../../assets/icons/switch-arrow-pink.svg';
import circledPlus from '../../../../assets/icons/circled-plus.svg';
import whiteArrow from '../../../../assets/icons/short-arrow-white.svg';
import noPosts from '../../../../assets/images/no-posts.png';

import useGlobalReload from '../../../../context/reload/reload.context';

import { UseHandlePosts } from '../../../../hooks/posts/useHandlePosts.hook';

import { AddPost, BaseScreen, Post, RightTab } from '../../../components';
import { Loader } from '../../../components/loader/loader.component';
import useGlobalLoading from '../../../../context/load/loading.context';

export function Timeline() {
  const [reload] = useGlobalReload();
  const [isLoading] = useGlobalLoading();
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
    isEmpty,
  } = UseHandlePosts();

  useEffect(() => {
    getPosts();
  }, [reload]);

  return (
    <BaseScreen at='home' rightTab={true}>
      {isLoading ? (
        <Loader />
      ) : (
        <>
          <section className='timeline-container'>
            {isEmpty ? (
              <div className='no-posts'>
                <p>
                  No momento não há nada por aqui.
                  <br />
                  Que tal fazer um post?
                </p>
                <img src={noPosts} />
              </div>
            ) : (
              posts.map((post) => <Post key={post?.id} post={post} />)
            )}
            <AddPost isOpen={addPostIsOpen} onClose={closeAddPost} />
          </section>
        </>
      )}
      <RightTab>
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
        {hasNextPage || hasPreviousPage ? (
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
        ) : null}
      </RightTab>
    </BaseScreen>
  );
}
