import { RouterProvider } from 'react-router-dom';
import { router } from './router';
import { GlobalUserProvider } from './context/user/user.context';

function App() {
  return (
    <GlobalUserProvider>
      <RouterProvider router={router} />
    </GlobalUserProvider>
  );
}

export default App;
