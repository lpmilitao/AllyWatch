import './reportPost.style.css';

import { SendButton, XButton } from '../';
import { UseHandlePosts } from '../../../hooks/posts/useHandlePosts.hook';

export function ReportPost({ postId, isOpen, onClose }) {
  const { onChangeReport, report, submitReport } = UseHandlePosts(postId);
  return (
    <dialog open={isOpen} className='report'>
      <XButton onClick={onClose} />
      <h1>Denuncie esse post</h1>
      <p>
        Conte-nos porquê esse post contêm algum conteúdo ofensivo ou não deveria
        estar na plataforma, por exemplo: conteúdo impróprio, conteúdo de ódio,
        spam, divulga informações pessoais de alguém, etc.
      </p>
      <textarea
        placeholder='Digite aqui...'
        onChange={onChangeReport}
        value={report}
      />
      <SendButton text={'Enviar'} onClick={submitReport} />
    </dialog>
  );
}
