INSERT INTO Market.Product(Name, Description)
SELECT 'kettle', 'new kettle'
UNION ALL SELECT 'iron', 'new iron'
UNION ALL SELECT 'phone', 'new phone'
UNION ALL SELECT 'vacuum', 'new vacuum'

INSERT INTO Market.Store(Name, Price, Count)
SELECT 'kettle', 200, 10
UNION ALL SELECT 'iron', 300, 5
UNION ALL SELECT 'phone', 1499, 1
UNION ALL SELECT 'vacuum', 700, 2

