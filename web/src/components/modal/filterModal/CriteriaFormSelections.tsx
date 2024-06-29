import { Criteria, CriteriaType } from "../../../types/Criteria";
import { capitalizeFirstLetter } from "../../../utils/stringUtils";
import { Button } from "../../button/Button";

interface CriteriaFormSelectionsProps {
	criteriaList: Criteria[];
	setCriteriaList: (criteriaList: Criteria[]) => void;
	setCriteriaListDeleted: (deleted: boolean) => void;
}

export const CriteriaFormSelections = (props: CriteriaFormSelectionsProps) => {
	const { criteriaList, setCriteriaList, setCriteriaListDeleted } = props;

	const conditionOptions: { [key: string]: string[] } = {
		[CriteriaType.AMOUNT]: ["Greater than", "Less than", "Equal"],
		[CriteriaType.TITLE]: ["Starts with", "Ends with", "Contains"],
		[CriteriaType.DATE]: ["From", "To", "At the date"],
	};

	const handleRemoveCriteria = (index: number) => {
		const updatedCriteriaList = criteriaList.filter((_, i) => i !== index);
		setCriteriaList(updatedCriteriaList);
		setCriteriaListDeleted(updatedCriteriaList.length === 0);
	};

	const handleCriteriaChange = (
		index: number,
		field: keyof Criteria,
		value: string
	) => {
		setCriteriaList(
			criteriaList.map((criteria, i) => {
				if (i === index) {
					return { ...criteria, [field]: value };
				}
				return criteria;
			})
		);
	};

	return (
		<div className="form-content">
			{criteriaList && criteriaList.length !== 0
				? criteriaList.map((criteria, index) => (
						<div key={index} className="criteria-group">
							<select
								name={`criteria[${index}].type`}
								value={criteria.type}
								onChange={(e) =>
									handleCriteriaChange(
										index,
										"type",
										e.target.value as CriteriaType
									)
								}
								required
							>
								<option value="">Select type</option>
								{Object.values(CriteriaType).map((option) => (
									<option key={option} value={option}>
										{capitalizeFirstLetter(option)}
									</option>
								))}
							</select>
							{criteria.type && (
								<select
									name={`criteria[${index}].condition`}
									value={criteria.condition}
									onChange={(e) =>
										handleCriteriaChange(index, "condition", e.target.value)
									}
								>
									<option value="">Select condition</option>
									{conditionOptions[criteria.type].map((option: string) => (
										<option key={option} value={option}>
											{option}
										</option>
									))}
								</select>
							)}
							{criteria.type === CriteriaType.AMOUNT && (
								<input
									type="number"
									name={`criteria[${index}].value`}
									value={criteria.value}
									onChange={(e) =>
										handleCriteriaChange(index, "value", e.target.value)
									}
								/>
							)}
							{criteria.type === CriteriaType.TITLE && (
								<input
									type="text"
									name={`criteria[${index}].value`}
									value={criteria.value}
									onChange={(e) =>
										handleCriteriaChange(index, "value", e.target.value)
									}
								/>
							)}
							{criteria.type === CriteriaType.DATE && (
								<input
									type="date"
									name={`criteria[${index}].value`}
									value={criteria.value}
									onChange={(e) =>
										handleCriteriaChange(index, "value", e.target.value)
									}
								/>
							)}
							<Button
								title="-"
								handleOnClick={() => handleRemoveCriteria(index)}
								className="delete-button"
							/>
						</div>
				  ))
				: "When no criteria is added, the filter will be deleted on saving."}
		</div>
	);
};
