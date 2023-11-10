import './commentList.style.css';

import { useEffect } from 'react';

import send from '../../../assets/icons/send-pink.svg';

import useGlobalReload from '../../../context/reload/reload.context';

import { useHandleComments } from '../../../hooks/posts/useHandleComments.hook';

import { XButton } from '../xButton/xButton.component';

export function CommentList({ close, isOpen, postId }) {
  const [reload] = useGlobalReload();
  const { comments, newComment, addCommentToPost, onChange, getComments } =
    useHandleComments(postId);

  useEffect(() => {
    if (isOpen) {
      getComments();
    }
  }, [reload, isOpen]);

  return (
    <dialog open={isOpen} className='comment-list'>
      <XButton onClick={close} />
      <div className='list'>
        {comments?.map((comment) => {
          return (
            <div className='comment'>
              <header>
                <h3>{comment?.author}</h3>
                <h6>{comment?.publicationTime}</h6>
              </header>
              <p>{comment?.comment}</p>
            </div>
          );
        })}
      </div>
      <div className='new-comment'>
        <input
          type='text'
          name='comment'
          onChange={onChange}
          value={newComment}
        />
        <img src={send} onClick={addCommentToPost} />
      </div>
    </dialog>
  );
}
