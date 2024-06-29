import React from "react";
import { Button } from "../button/Button";
import "./modal.scss";

interface ModalProps {
	show: boolean;
	handleClose: () => void;
	children: React.ReactNode;
	title: string;
	modalFooter?: React.ReactNode;
}

export const Modal: React.FC<ModalProps> = ({
	show,
	handleClose,
	children,
	title,
	modalFooter,
}) => {
	return (
		<div className={`modal ${show ? "display-block" : "display-none"}`}>
			<div className="modal-main">
				<div className="modal-header">
					<h1>{title}</h1>
					<Button className="x-button" handleOnClick={handleClose} title="X" />
				</div>
				<div className="modal-content">{children}</div>
				<div className="modal-footer">{modalFooter}</div>
			</div>
		</div>
	);
};
