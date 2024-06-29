import './filterForm.scss';

interface FilterSelectionProps {
	selectedSequence: number;
	handleSequenceChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

export const FilterSelection = (props: FilterSelectionProps) => {
	const { selectedSequence, handleSequenceChange } = props;
	return (
		<div className="radio-group-form">
			<div className="radio-group">
				<label>
					<input
						type="radio"
						name="sequence"
						value="1"
						checked={selectedSequence === 1}
						onChange={handleSequenceChange}
					/>
					Select 1
				</label>
				<label>
					<input
						type="radio"
						name="sequence"
						value="2"
						checked={selectedSequence === 2}
						onChange={handleSequenceChange}
					/>
					Select 2
				</label>
				<label>
					<input
						type="radio"
						name="sequence"
						value="3"
						checked={selectedSequence === 3}
						onChange={handleSequenceChange}
					/>
					Select 3
				</label>
			</div>
		</div>
	);
};
