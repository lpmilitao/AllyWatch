import { RouterProvider } from 'react-router-dom';
import { router } from './router';
import { GlobalUserProvider } from './context/user/user.context';
import { GlobalReloadProvider } from './context/reload/reload.context';
import { GlobalLoadingProvider } from './context/load/loading.context';

function App() {
  return (
    <GlobalLoadingProvider>
      <GlobalReloadProvider>
        <GlobalUserProvider>
          <RouterProvider router={router} />
        </GlobalUserProvider>
      </GlobalReloadProvider>
    </GlobalLoadingProvider>
  );
}

export default App;
