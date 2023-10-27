import { createBrowserRouter } from 'react-router-dom';
import {
  ChooseType,
  Home,
  SignIn,
  SpecialistSignUp,
  UserSignUp,
} from '../ui/screens';

export const router = createBrowserRouter([
  {
    path: '/home',
    element: <Home />,
  },
  {
    path: '/signin',
    element: <SignIn />,
  },
  {
    path: '/signup',
    element: <ChooseType />,
  },
  {
    path: '/signup-user',
    element: <UserSignUp />,
  },
  {
    path: '/signup-specialist',
    element: <SpecialistSignUp />,
  },
]);
