import './rightTab.style.css';

export function RightTab({ children, className }) {
  return (
    <section className={`right-tab-container ${className}`}>{children}</section>
  );
}
