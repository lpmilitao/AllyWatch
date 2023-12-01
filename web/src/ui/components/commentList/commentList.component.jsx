import './commentList.style.css';

import { useEffect } from 'react';

import send from '../../../assets/icons/send-pink.svg';

import useGlobalReload from '../../../context/reload/reload.context';

import { useHandleComments } from '../../../hooks/posts/useHandleComments.hook';

import { XButton } from '../xButton/xButton.component';

export function CommentList({ close, isOpen, postId }) {
  const [reload] = useGlobalReload();
  const {
    comments,
    newComment,
    addCommentToPost,
    onChange,
    getComments,
    deleteCommentFromPost,
  } = useHandleComments(postId);

  useEffect(() => {
    if (isOpen) {
      getComments();
    }
  }, [reload, isOpen]);

  function handleKeyPress(e) {
    if (e.key === 'Enter') {
      addCommentToPost(e);
    }
  }

  return (
    <dialog open={isOpen} className='comment-list'>
      <XButton onClick={close} />
      <div className='list'>
        {comments?.map((comment) => {
          return (
            <div className='comment'>
              <header>
                <h3>{comment?.author}</h3>
                {comment?.mine ? (
                  <h6
                    className='delete-comment'
                    onClick={() => deleteCommentFromPost(comment?.id)}
                  >
                    Excluir
                  </h6>
                ) : null}
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
          onKeyPress={handleKeyPress}
        />
        <img src={send} onClick={addCommentToPost} />
      </div>
    </dialog>
  );
}
