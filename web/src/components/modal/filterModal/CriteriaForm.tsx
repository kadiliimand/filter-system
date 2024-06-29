import React, { useEffect } from "react";
import { Criteria, CriteriaType } from "../../../types/Criteria";
import { Button } from "../../button/Button";
import { CriteriaFormSelections } from "./CriteriaFormSelections";
import "./criteriaForm.scss";

interface CriteriaFormProps {
	criteriaList: Criteria[];
	setCriteriaList: (criteriaList: Criteria[]) => void;
	criteriaListDeleted: boolean;
	setCriteriaListDeleted: (deleted: boolean) => void;
}

export const CriteriaForm = (props: CriteriaFormProps) => {
	const {
		criteriaList,
		setCriteriaList,
		criteriaListDeleted,
		setCriteriaListDeleted,
	} = props;

	useEffect(() => {
		if (criteriaList && criteriaList.length === 0 && !criteriaListDeleted) {
			setCriteriaList([
				{
					id: 0,
					filterId: 0,
					type: CriteriaType.AMOUNT,
					condition: "",
					value: "",
				},
			]);
		}
	}, [criteriaList, setCriteriaList]);

	const handleAddCriteria = (e: React.MouseEvent<HTMLButtonElement>) => {
		e.preventDefault();
		setCriteriaList([
			...criteriaList,
			{
				id: 0,
				filterId: 0,
				type: CriteriaType.AMOUNT,
				condition: "",
				value: "",
			},
		]);
	};

	return (
		<div>
			<CriteriaFormSelections
				criteriaList={criteriaList}
				setCriteriaList={setCriteriaList}
				setCriteriaListDeleted={setCriteriaListDeleted}
			/>
			<div className="add-button">
				<Button
					title="+ Add Row"
					handleOnClick={handleAddCriteria}
					className="button close-button"
				/>
			</div>
		</div>
	);
};
