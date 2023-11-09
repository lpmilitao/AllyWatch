import { useState } from 'react';

import { toast } from 'react-toastify';

import useGlobalUser from '../../context/user/user.context';
import useGlobalReload from '../../context/reload/reload.context';

import { listAllPosts } from '../../external/server';

export function UseGetPosts() {
  const [posts, setPosts] = useState([]);
  const [globarUser] = useGlobalUser();
  const [order, setOrder] = useState('publicationTime');
  const [page, setPage] = useState(0);
  const [hasNextPage, setHasNextPage] = useState(false);
  const [hasPreviousPage, setHasPreviousPage] = useState(false);
  const [totalPages, setTotalPages] = useState(0);
  const [reload, setReload] = useGlobalReload();

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
    if (page - 1 <= 0) return;

    setPage(page - 1);
    setReload(!reload);
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
  };
}
