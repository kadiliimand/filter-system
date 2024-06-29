export interface Criteria {
	id: number;
	filterId: number;
	type: CriteriaType;
	condition: string;
	value: string;
}

export enum CriteriaType {
	TITLE = "TITLE",
	AMOUNT = "AMOUNT",
	DATE = "DATE",
}
