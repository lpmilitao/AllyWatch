import { useState } from 'react';

import { toast } from 'react-toastify';

import useGlobalUser from '../../context/user/user.context';
import useGlobalReload from '../../context/reload/reload.context';

import { createNewPost, listAllPosts, reportPost } from '../../external/server';
import { listUsersPosts } from '../../external/server/post/listUsersPosts';

export function UseHandlePosts(postId) {
  const [globarUser] = useGlobalUser();
  const [reload, setReload] = useGlobalReload();

  const [posts, setPosts] = useState([]);
  const [order, setOrder] = useState('publicationTime');
  const [page, setPage] = useState(0);
  const [hasNextPage, setHasNextPage] = useState(false);
  const [hasPreviousPage, setHasPreviousPage] = useState(false);
  const [totalPages, setTotalPages] = useState(0);
  const [newPost, setNewPost] = useState({
    body: '',
    aggressor: '',
    anonymous: false,
  });
  const [addPostIsOpen, setAddPostIsOpen] = useState(false);
  const [report, setReport] = useState('');

  async function getPosts() {
    try {
      const response = await listAllPosts(globarUser, order, page);

      setHasNextPage(!response.last);
      setHasPreviousPage(!response.first);
      setTotalPages(response.totalPages);
      setPosts(response.content);
    } catch (error) {
      toast.error('Ocorreu um erro na busca dos posts.', {
        position: toast.POSITION.TOP_RIGHT,
      });
    }
  }

  function switchOrder() {
    if (order === 'publicationTime') {
      setOrder('likes');
    } else {
      setOrder('publicationTime');
    }
    setReload(!reload);
  }

  function nextPage() {
    if (!hasNextPage) return;
    if (page + 1 > totalPages) return;

    setPage(page + 1);
    setReload(!reload);
  }

  function previousPage() {
    if (!hasPreviousPage) return;
    if (page - 1 < 0) return;

    setPage(page - 1);
    setReload(!reload);
  }

  async function handleAddNewPost(event) {
    event.preventDefault();
    try {
      await createNewPost(globarUser, newPost);
      toast.success('Post criado com sucesso!', {
        position: toast.POSITION.TOP_RIGHT,
      });
      setReload(!reload);
      setNewPost({
        body: '',
        aggressor: '',
        anonymous: false,
      });
    } catch (error) {
      toast.error('Ocorreu um erro na criação do post.', {
        position: toast.POSITION.TOP_RIGHT,
      });
    }
  }

  function onChangeNewPost(event) {
    setNewPost({
      ...newPost,
      [event.target.name]: event.target.value,
    });
  }

  function closeAddPost() {
    setAddPostIsOpen(!addPostIsOpen);
  }

  function onChangeCheckbox(event) {
    const { checked } = event.target;

    if (checked) {
      setNewPost({
        ...newPost,
        anonymous: true,
      });
    } else {
      setNewPost({
        ...newPost,
        anonymous: false,
      });
    }
  }

  function onChangeReport(event) {
    setReport(event.target.value);
  }

  async function submitReport(event) {
    event.preventDefault();

    if (report.trim() === '') {
      toast.error('Por favor, informe o motivo da sua denúncia.', {
        position: toast.POSITION.TOP_RIGHT,
      });
      return;
    }

    try {
      await reportPost(globarUser, postId, report);
      toast.success('Post denunciado com sucesso!', {
        position: toast.POSITION.TOP_RIGHT,
      });
    } catch (error) {
      toast.error('Ocorreu um erro na denúncia do post.', {
        position: toast.POSITION.TOP_RIGHT,
      });
    }
  }

  async function getMyPosts() {
    try {
      const response = await listUsersPosts(globarUser);

      setHasNextPage(!response.last);
      setHasPreviousPage(!response.first);
      setTotalPages(response.totalPages);
      setPosts(response.content);
    } catch (error) {
      toast.error('Ocorreu um erro na busca dos posts.', {
        position: toast.POSITION.TOP_RIGHT,
      });
    }
  }

  return {
    posts,
    getPosts,
    order,
    switchOrder,
    page,
    nextPage,
    previousPage,
    hasNextPage,
    hasPreviousPage,
    handleAddNewPost,
    onChangeNewPost,
    newPost,
    addPostIsOpen,
    closeAddPost,
    onChangeCheckbox,
    onChangeReport,
    report,
    submitReport,
    getMyPosts,
  };
}
