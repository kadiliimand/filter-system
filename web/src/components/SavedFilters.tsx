
import { Filter } from "../types/Filter";
import { capitalizeFirstLetter } from "../utils/stringUtils";

interface SavedFiltersProps {
	filters: Filter[];
}

export const SavedFilters = (props: SavedFiltersProps) => {
	const { filters } = props;

	const sortedFilters = filters.sort((a, b) => a.sequence - b.sequence);

	return (
		<div>
			{sortedFilters.map((filter) => (
				<div key={filter.sequence}>
					<h4>{filter.title}</h4>
					<ul>
						{filter.criteriaList &&
							filter.criteriaList.map((criteria) => (
								<li key={criteria.id}>
									{capitalizeFirstLetter(criteria.type)} {criteria.condition}{" "}
									{criteria.value}
								</li>
							))}
					</ul>
				</div>
			))}
		</div>
	);
};
