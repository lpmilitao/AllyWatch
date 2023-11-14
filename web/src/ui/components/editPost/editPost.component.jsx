import './editPost.style.css';

import { UseHandlePosts } from '../../../hooks/posts/useHandlePosts.hook';

import { SendButton, XButton } from '../';
import { UseHandleEditPost } from '../../../hooks/posts/useHandleEditPost.hook';
import { useEffect } from 'react';

export function EditPost({ postId, onClose }) {
  const { post, getPost, onChange, onChangeCheckbox, handleEditPost } =
    UseHandleEditPost(postId);

  useEffect(() => {
    getPost();
  }, []);

  return (
    <dialog open className='edit-post-container'>
      <XButton onClick={onClose} />
      <form>
        <div className='textarea'>
          <h3>Relato</h3>
          <textarea name='body' value={post?.body} onChange={onChange} />
        </div>
        <div className='aggressor'>
          <h3>Agressor</h3>
          <p>
            Lembre-se que esse campo é opcional, só preencha-o se você se sentir
            confortável. Pedimos isso para que possamos conectá-lo com vítimas
            dessa mesma pessoa, e essa informação não será divulgada em nenhuma
            parte da plataforma.
          </p>
          <input
            type='text'
            value={post?.aggressor}
            name='aggressor'
            onChange={onChange}
          />
        </div>
        <div className='anonymous'>
          <h3>Post anônimo?</h3>
          <input
            type='checkbox'
            name='anonymous'
            onChange={onChangeCheckbox}
            checked={post?.anonymous}
          />
          <p>Quando você seleciona essa opção seu nome não será divulgado.</p>
        </div>
        <SendButton onClick={handleEditPost} text={'Enviar'} />
      </form>
    </dialog>
  );
}
