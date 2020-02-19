INSERT INTO product_db.product
(product_id, category_id, description, imageurl, is_avilable, merchant_id, msrp, price, product_page_url, title)
SELECT 1, 2, 'Nothing will turn his head faster than you wearing the sexy Pryme pump from Steven by Steve Madden. This daring pump has a pretty patent leather upper with light shirring, a double stitch detail surrounding the collar, and a vampy almond shaped toe.','http://www.lifestylelabels.com/media/catalog/product/cache/1/small_image/210x/9df78eab33525d08d6e5fb8d27136e95/s/t/steven-by-steve-madden-pryme-pump.jpg',true,1,0,299,'http://www.lifestylelabels.com/steven-by-steve-madden-pryme-pump.html','elegant red pump'
WHERE NOT EXISTS (select product_id from product_db.product WHERE product_id = 1);

INSERT INTO product_db.product
(product_id, category_id, description, imageurl, is_avilable, merchant_id, msrp, price, product_page_url, title)
SELECT 2, 2, 'The Lucero pump from Nine West may just leave him at a loss for words. This flirty pump has a leather upper, a pretty almond-shaped toe with a slight V-cut vamp, leather linings, and a cushioned insole for long-wearing comfort.','http://www.lifestylelabels.com/media/catalog/product/cache/1/small_image/210x/9df78eab33525d08d6e5fb8d27136e95/n/i/nine-west-women-s-lucero-pump.jpg',true,1,0,289.99,'http://www.lifestylelabels.com/nine-west-women-s-lucero-pump.html','glittering red pump'
WHERE NOT EXISTS (select product_id from product_db.product WHERE product_id = 2);

INSERT INTO product_db.product
(product_id, category_id, description, imageurl, is_avilable, merchant_id, msrp, price, product_page_url, title)
SELECT 3, 5, 'Sporting with style, this is a durable and super-comfortable golf shoe built for performance.','http://www.lifestylelabels.com/media/catalog/product/e/c/ecco-womens-golf-flexor-golf-shoe.jpg',false,2,0,159,'http://www.lifestylelabels.com/womens-golf-shoes.html','womens golf shoes'
WHERE NOT EXISTS (select product_id from product_db.product WHERE product_id = 3);

UPDATE product_db.hibernate_sequence
SET next_val=4
WHERE next_val = 1;