import React, { useEffect, useState } from "react";
import { Criteria } from "../../../types/Criteria";
import { Filter } from "../../../types/Filter";
import {
    isValidTitle
} from "../../../utils/validationUtils";
import { CriteriaForm } from "./CriteriaForm";
import { FilterSelection } from "./FilterSelection";
import "./filterForm.scss";

interface FilterFormProps {
	filter: Filter;
	setFilter: (filter: Filter) => void;
	selectedSequence: number;
	setSelectedSequence: (sequence: number) => void;
  setError: (error: string | null) => void;
}

export const FilterForm = (props: FilterFormProps) => {
	const { filter, setFilter, selectedSequence, setSelectedSequence, setError} = props;
	const [criteriaListDeleted, setCriteriaListDeleted] =
		useState<boolean>(false);

	useEffect(() => {
		setCriteriaListDeleted(false);
	}, [selectedSequence]);

	const handleTitleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const newTitle = e.target.value;
		if (!isValidTitle(newTitle)) {
			setError(
				"Invalid title. Please use only letters, numbers, spaces, and basic punctuation."
			);
		}
		setFilter({ ...filter, title: newTitle });
	};

	const handleCriteriaChange = (criteriaList: Criteria[]) => {
		setFilter({ ...filter, criteriaList });
	};

	const handleSequenceChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		setSelectedSequence(parseInt(e.target.value, 10));
	};

	return (
		<table className="form-table">
			<tbody>
				<tr>
					<td>
						<label className="label">Filter name</label>
					</td>
					<td>
						<input
							type="text"
							id="title"
							name="title"
							required
							value={
								filter && filter.title
									? filter.title
									: `My Filter ${filter && filter.sequence}`
							}
							onChange={handleTitleChange}
							className="input"
						/>
						<span className="required">*</span>
					</td>
				</tr>
				<tr>
					<td className="criteria-label">
						<label className="label">Criteria</label>
					</td>
					<td>
						<CriteriaForm
							criteriaList={filter && filter.criteriaList}
							setCriteriaList={handleCriteriaChange}
							criteriaListDeleted={criteriaListDeleted}
							setCriteriaListDeleted={setCriteriaListDeleted}
						/>
					</td>
				</tr>
				<tr>
					<td>
						<label className="label">Selection</label>
					</td>
					<td>
						<FilterSelection
							selectedSequence={selectedSequence}
							handleSequenceChange={handleSequenceChange}
						/>
					</td>
				</tr>
			</tbody>
		</table>
	);
};
