INSERT INTO category (id, description, name) VALUES
    (nextval('category_seq'), 'Computers including desktops and laptops', 'Computers'),
    (nextval('category_seq'), 'Computer peripherals and accessories', 'Computer Accessories'),
    (nextval('category_seq'), 'Software products for various uses', 'Software');

INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES
    (nextval('product_seq'), 'High-performance gaming laptop', 'Gaming Laptop', 10, 1499.99, (SELECT id FROM category WHERE name = 'Computers')),
    (nextval('product_seq'), 'Powerful desktop computer for professionals', 'Desktop Computer', 15, 1299.99, (SELECT id FROM category WHERE name = 'Computers')),
    (nextval('product_seq'), 'Lightweight ultrabook for travel', 'Ultrabook', 20, 999.99, (SELECT id FROM category WHERE name = 'Computers')),
    (nextval('product_seq'), 'Budget-friendly student laptop', 'Student Laptop', 50, 499.99, (SELECT id FROM category WHERE name = 'Computers')),
    (nextval('product_seq'), '2-in-1 tablet with detachable keyboard', '2-in-1 Tablet', 30, 699.99, (SELECT id FROM category WHERE name = 'Computers'));

INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES
    (nextval('product_seq'), 'Wireless mouse', 'Mouse', 100, 25.99, (SELECT id FROM category WHERE name = 'Computer Accessories')),
    (nextval('product_seq'), 'Mechanical keyboard with RGB lighting', 'Keyboard', 50, 79.99, (SELECT id FROM category WHERE name = 'Computer Accessories')),
    (nextval('product_seq'), '27-inch 4K monitor', 'Monitor', 25, 399.99, (SELECT id FROM category WHERE name = 'Computer Accessories')),
    (nextval('product_seq'), 'External hard drive 1TB', 'External HDD', 75, 59.99, (SELECT id FROM category WHERE name = 'Computer Accessories')),
    (nextval('product_seq'), 'USB-C docking station', 'Docking Station', 40, 129.99, (SELECT id FROM category WHERE name = 'Computer Accessories'));

INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES
    (nextval('product_seq'), 'Professional photo editing software', 'Photo Editor', 200, 89.99, (SELECT id FROM category WHERE name = 'Software')),
    (nextval('product_seq'), 'Video editing software for beginners', 'Video Editor', 150, 49.99, (SELECT id FROM category WHERE name = 'Software')),
    (nextval('product_seq'), 'Antivirus software with 1-year license', 'Antivirus', 300, 29.99, (SELECT id FROM category WHERE name = 'Software')),
    (nextval('product_seq'), 'Productivity suite (word processor, spreadsheet, etc.)', 'Productivity Suite', 250, 199.99, (SELECT id FROM category WHERE name = 'Software')),
    (nextval('product_seq'), '3D modeling software for professionals', '3D Modeling Software', 100, 299.99, (SELECT id FROM category WHERE name = 'Software'));