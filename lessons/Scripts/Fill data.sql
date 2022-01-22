INSERT INTO Market.Product(Name, Description)
SELECT 'kettle', 'new kettle'
UNION ALL SELECT 'iron', 'new iron'
UNION ALL SELECT 'phone', 'new phone'
UNION ALL SELECT 'vacuum', 'new vacuum'

INSERT INTO Market.Store(ProductId, Price, Count)
SELECT 1, 200, 10
UNION ALL SELECT 2, 300, 5
UNION ALL SELECT 3, 1499, 1
UNION ALL SELECT 4, 700, 2


