import { useState } from "react";
import { Filter } from "../types/Filter";

interface UseFilterResult {
	filters: Filter[];
	error: string | null;
	success: string | null;
	fetchFilters: () => Promise<void>;
	createFilter: (filter: Filter) => Promise<void>;
	updateFilter: (filter: Filter) => Promise<void>;
	removeFilter: (id: number) => Promise<void>;
}

const handleError = (error: unknown): string => {
	if (error instanceof Error) {
		return error.message;
	}
	return String(error);
};

export const useFilter = (): UseFilterResult => {
	const [filters, setFilters] = useState<Filter[]>([]);
	const [error, setError] = useState<string | null>(null);
	const [success, setSuccess] = useState<string | null>(null);

	const clearMessages = () => {
		setTimeout(() => {
			setError(null);
			setSuccess(null);
		}, 3000);
	};

	const fetchFilters = async () => {
		try {
			const response = await fetch("/filters");
			if (!response.ok) {
				throw new Error("Failed to fetch filters");
			}
			const data = await response.json();
			setFilters(data);
		} catch (error) {
			setError(handleError(error));
			clearMessages();
		}
	};

	const createFilter = async (filter: Filter) => {
		try {
			const response = await fetch("/filters", {
				method: "POST",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify(filter),
			});
			if (!response.ok) {
				throw new Error("Failed to create filter");
			}
			await fetchFilters();
			setSuccess("Filter created successfully!");
			clearMessages();
		} catch (error) {
			setError(handleError(error));
			clearMessages();
		}
	};

	const updateFilter = async (filter: Filter) => {
		try {
			const response = await fetch(`/filters`, {
				method: "PUT",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify(filter),
			});
			if (!response.ok) {
				throw new Error("Failed to update filter");
			}
			await fetchFilters();
			setSuccess("Filter updated successfully!");
			clearMessages();
		} catch (error) {
			setError(handleError(error));
			clearMessages();
		}
	};

	const removeFilter = async (id: number) => {
		try {
			const response = await fetch(`/filters/${id}`, {
				method: "DELETE",
			});
			if (!response.ok) {
				throw new Error("Failed to delete filter");
			}
			await fetchFilters();
			setSuccess("Filter removed successfully!");
			clearMessages();
		} catch (error) {
			setError(handleError(error));
			clearMessages();
		}
	};

	return {
		filters,
		error,
		success,
		fetchFilters,
		createFilter,
		updateFilter,
		removeFilter,
	};
};
