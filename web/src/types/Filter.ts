import { Criteria } from "./Criteria";

export interface Filter {
	id: number;
	sequence: number;
	title: string;
	criteriaList: Criteria[];
}
