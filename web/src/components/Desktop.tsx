import { useEffect, useMemo, useState } from "react";
import { useFilter } from "../hooks/useFilter";
import { Filter } from "../types/Filter";
import { SavedFilters } from "./SavedFilters";
import { Button } from "./button/Button";
import "./desktop.scss";
import { Modal } from "./modal/Modal";
import { FilterModal } from "./modal/filterModal/FilterModal";

export const Desktop = () => {
	const [showModal, setShowModal] = useState(false);
	const {
		filters,
		error,
		success,
		fetchFilters,
		createFilter,
		updateFilter,
		removeFilter,
	} = useFilter();
	const [selectedSequence, setSelectedSequence] = useState<number>(1);
	const [filter, setFilter] = useState<Filter>(
		filters.length > 0
			? filters[0]
			: {
					id: 0,
					sequence: 1,
					title: "",
					criteriaList: [],
			  }
	);

	useEffect(() => {
		const selectedFilter = filters.find(
			(filter) => filter.sequence === selectedSequence
		);
		setFilter(
			selectedFilter
				? selectedFilter
				: { id: 0, sequence: selectedSequence, title: "", criteriaList: [] }
		);
	}, [selectedSequence, filters]);

	useEffect(() => {
		fetchFilters();
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

	const handleOpenModal = () => {
		setShowModal(true);
	};

	const isSaveButtonDisabled = useMemo(() => {
		const areAllCriteriaFilled = filter?.criteriaList.every(
			(criteria) => !!criteria.type && !!criteria.condition && !!criteria.value
		);
		return !areAllCriteriaFilled;
	}, [filter]);

	const handleCloseModal = () => {
		setShowModal(false);
		setSelectedSequence(1);
		setFilter({
			id: 0,
			sequence: 1,
			title: "",
			criteriaList: [],
		});
	};

	const handleSaveFilter = async () => {
		const updatedFilter = {
			...filter,
			title:
				filter && filter.title
					? filter.title
					: `My Filter ${filter && filter.sequence}`,
		};
		if (filter.id) {
			if (filter.criteriaList.length === 0) {
				await removeFilter(filter.id);
			} else {
				await updateFilter(updatedFilter);
			}
		} else {
			await createFilter(updatedFilter);
		}
		handleCloseModal();
	};

	return (
		<div className="container">
			{error && <p className="error">{error}</p>}
			{success && <p className="success">{success}</p>}
			<Button
				title="Add Filter"
				handleOnClick={handleOpenModal}
				className="button save-button"
			/>
			<Modal
				show={showModal}
				handleClose={handleCloseModal}
				title="Filter"
				modalFooter={
					<>
						<Button
							title="Close"
							handleOnClick={handleCloseModal}
							className="button close-button"
						/>
						<Button
							title="Save"
							handleOnClick={handleSaveFilter}
							className="button save-button"
							disabled={isSaveButtonDisabled}
						/>
					</>
				}
			>
				<FilterModal
					filter={filter}
					setFilter={setFilter}
					selectedSequence={selectedSequence}
					setSelectedSequence={setSelectedSequence}
				/>
			</Modal>
			{filters.length === 0 ? (
				<h3>You don't have any saved filters</h3>
			) : (
				<h3>Your saved Filters</h3>
			)}
			<SavedFilters filters={filters} />
		</div>
	);
};
