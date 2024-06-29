export const isValidTitle = (title: string): boolean => {
	const titleRegex = /^[a-zA-Z0-9\s.,-]*$/;
	return titleRegex.test(title);
};

export const isValidDate = (date: string): boolean => {
	const dateRegex = /^\d{4}-\d{2}-\d{2}$/;
	return dateRegex.test(date);
};

export const sanitizeInput = (input: string): string => {
	const sanitizedInput = input.replace(/<[^>]*>?/gm, "");
	return sanitizedInput;
};
