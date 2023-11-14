import './addPost.style.css';

import { UseHandlePosts } from '../../../hooks/posts/useHandlePosts.hook';
import { XButton, SendButton } from '../';

export function AddPost({ isOpen, onClose }) {
  const { handleAddNewPost, onChangeNewPost, newPost, onChangeCheckbox } =
    UseHandlePosts();

  return (
    <dialog open={isOpen} className='add-post-container'>
      <XButton onClick={onClose} />
      <form>
        <h1>Adicione um post</h1>
        <div className='textarea'>
          <h3>Conte-nos seu relato</h3>
          <textarea
            name='body'
            value={newPost.body}
            onChange={onChangeNewPost}
          />
        </div>
        <div className='aggressor'>
          <h3>Escreva o nome do seu agressor</h3>
          <p>
            Esse campo é opcional, só preencha-o se você se sentir confortável.
            Pedimos isso para que possamos conectá-lo com vítimas dessa mesma
            pessoa, e essa informação não será divulgada em nenhuma parte da
            plataforma.
          </p>
          <input
            type='text'
            value={newPost.aggressor}
            name='aggressor'
            onChange={onChangeNewPost}
          />
        </div>
        <div className='anonymous'>
          <h3>Quer compartilhar esse post de forma anônima?</h3>
          <input type='checkbox' name='anonymous' onChange={onChangeCheckbox} />
          <p>Quando você seleciona essa opção seu nome não será divulgado.</p>
        </div>
        <SendButton onClick={handleAddNewPost} text={'Enviar'} />
      </form>
    </dialog>
  );
}
