import classes from "./Modal.module.css";

const Modal = ({ children, onClose }) => {
  return (
    <>
      <div className={classes.backdrop} onClick={onClose} />
      <dialog open={true} className={classes.modal}>
        {children}
      </dialog>
    </>
  );
};

export default Modal;
