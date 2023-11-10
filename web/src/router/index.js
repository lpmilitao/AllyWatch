import { createBrowserRouter } from 'react-router-dom';
import {
  Chatbot,
  Chats,
  ChooseType,
  HelpPoints,
  Home,
  Profile,
  Report,
  SignIn,
  Specialist,
  SpecialistSignUp,
  Timeline,
  UserSignUp,
} from '../ui/screens';
import { PrivateRoute } from './privateRoute';

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
  {
    path: '/',
    element: <PrivateRoute Screen={Timeline} />,
  },
  {
    path: '/me',
    element: <Profile />,
  },
  {
    path: '/specialists',
    element: <Specialist />,
  },
  {
    path: '/chatbot',
    element: <Chatbot />,
  },
  {
    path: '/report',
    element: <Report />,
  },
  {
    path: '/help-points',
    element: <HelpPoints />,
  },
  {
    path: '/chats',
    element: <Chats />,
  },
]);
