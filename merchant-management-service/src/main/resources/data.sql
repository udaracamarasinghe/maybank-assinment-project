INSERT INTO merchant_db.merchant
(mer_id, name)
SELECT 1, 'Reebok'
WHERE NOT EXISTS (select mer_id from merchant_db.merchant WHERE mer_id = 1);

INSERT INTO merchant_db.merchant
(mer_id, name)
SELECT 2, 'Ecco'
WHERE NOT EXISTS (select mer_id from merchant_db.merchant WHERE mer_id = 2);