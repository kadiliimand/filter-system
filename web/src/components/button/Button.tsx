import React from 'react';
import './button.scss';

interface ButtonProps {
    title: string;
    handleOnClick: (event: React.MouseEvent<HTMLButtonElement>) => void;
    className?: string;
    disabled?: boolean;
}

export const Button = (props: ButtonProps) => {
    const { title, handleOnClick, className, disabled } = props;
    return (
        <button
            onClick={handleOnClick}
            className={className}
            disabled={disabled}
        >
            {title}
        </button>
    );
};
