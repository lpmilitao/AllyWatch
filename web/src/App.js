import { RouterProvider } from 'react-router-dom';
import { router } from './router';
import { GlobalUserProvider } from './context/user/user.context';
import { GlobalReloadProvider } from './context/reload/reload.context';

function App() {
  return (
    <GlobalReloadProvider>
      <GlobalUserProvider>
        <RouterProvider router={router} />
      </GlobalUserProvider>
    </GlobalReloadProvider>
  );
}

export default App;
