import { useState } from 'react';
import { toast } from 'react-toastify';

import useGlobalUser from '../../context/user/user.context';
import useGlobalReload from '../../context/reload/reload.context';

import {
  commentOnPost,
  deleteComment,
  listPostsComments,
} from '../../external/server';

export function useHandleComments(postId) {
  const [user] = useGlobalUser();
  const [reload, setReload] = useGlobalReload();

  const [comments, setComments] = useState([]);
  const [newComment, setNewComment] = useState('');

  async function getComments() {
    try {
      const response = await listPostsComments(user, postId);
      setComments(response);
    } catch (error) {
      toast.error('Ocorreu um erro ao buscar os comentários deste post.', {
        position: toast.POSITION.TOP_RIGHT,
      });
    }
  }

  async function addCommentToPost(event) {
    event.preventDefault();
    try {
      await commentOnPost(user, postId, newComment);
      setReload(!reload);
      toast.success('Comentário feito com sucesso!', {
        position: toast.POSITION.BOTTOM_RIGHT,
      });
      setNewComment('');
    } catch (error) {
      toast.error('Ocorreu um erro ao fazer um comentário.', {
        position: toast.POSITION.TOP_RIGHT,
      });
    }
  }

  async function deleteCommentFromPost(commentId) {
    try {
      await deleteComment(user, commentId);
      toast.success('Comentário deletado com sucesso!', {
        position: toast.POSITION.BOTTOM_RIGHT,
      });
      setReload(!reload);
    } catch (error) {
      toast.error('Ocorreu um erro ao excluir o comentário.', {
        position: toast.POSITION.TOP_RIGHT,
      });
    }
  }

  function onChange(event) {
    setNewComment(event.target.value);
  }

  return {
    comments,
    newComment,
    addCommentToPost,
    onChange,
    getComments,
    deleteCommentFromPost,
  };
}
