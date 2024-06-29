import { useState } from "react";
import { Filter } from "../../../types/Filter";
import { FilterForm } from "./FilterForm";

interface FilterModalProps {
	filter: Filter | undefined;
	setFilter: (filter: Filter) => void;
	selectedSequence: number;
	setSelectedSequence: (sequence: number) => void;
}

export const FilterModal = (props: FilterModalProps) => {
	const { filter, setFilter, selectedSequence, setSelectedSequence } = props;
	const [error, setError] = useState<string | null>(null);

	return (
		<div>
			{error && <p className="error">{error}</p>}
			<FilterForm
				filter={filter!}
				setFilter={setFilter}
				selectedSequence={selectedSequence}
				setSelectedSequence={setSelectedSequence}
				setError={setError}
			/>
		</div>
	);
};
