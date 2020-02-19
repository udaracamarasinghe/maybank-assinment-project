INSERT INTO category_db.category
(cat_id, name, parent_cat_id_cat_id)
SELECT 1, 'Shoes', null
WHERE NOT EXISTS (select cat_id from category_db.category WHERE cat_id = 1);

INSERT INTO category_db.category
(cat_id, name, parent_cat_id_cat_id)
SELECT 2, 'Pump', 1
WHERE NOT EXISTS (select cat_id from category_db.category WHERE cat_id = 2);

INSERT INTO category_db.category
(cat_id, name, parent_cat_id_cat_id)
SELECT 3, 'For Women', null
WHERE NOT EXISTS (select cat_id from category_db.category WHERE cat_id = 3);

INSERT INTO category_db.category
(cat_id, name, parent_cat_id_cat_id)
SELECT 4, 'Shoes', 3
WHERE NOT EXISTS (select cat_id from category_db.category WHERE cat_id = 4);

INSERT INTO category_db.category
(cat_id, name, parent_cat_id_cat_id)
SELECT 5, 'Golf', 4
WHERE NOT EXISTS (select cat_id from category_db.category WHERE cat_id = 5);


