import createGlobalState from 'react-create-global-state';

const [useGlobalReload, Provider] = createGlobalState(false);

export const GlobalReloadProvider = Provider;

export default useGlobalReload;
