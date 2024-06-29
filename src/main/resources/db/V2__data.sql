INSERT INTO filter (filter_sequence, title) VALUES
                                                        (1, 'First Filter'),
                                                        (2, 'Second Filter');

INSERT INTO criteria (filter_id, type, condition, criterion_value) VALUES
                                                                                         ((SELECT id FROM filter WHERE filter_sequence = 1), 'AMOUNT', 'Greater than', '100'),
                                                                                         ((SELECT id FROM filter WHERE filter_sequence = 1), 'TITLE', 'Contains', 'example'),
                                                                                         ((SELECT id FROM filter WHERE filter_sequence = 2), 'DATE', 'From', '2023-12-31'),
                                                                                         ((SELECT id FROM filter WHERE filter_sequence = 2), 'AMOUNT', 'Less than', '500');