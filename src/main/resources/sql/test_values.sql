-- Test til user
INSERT INTO user (
    id, name
)
VALUES (1, 'Amanda'),
       (2, 'Sejr'),
       (3, 'Dan'); 

-- Test til list tabel
INSERT INTO list (
    id, name, user_id
)
VALUES 
(1, 'Dans ønskeliste, fødselsdag 2027', 3),
(2, 'Jul 2026, Sejr', 2),
(3, 'Amandas ønskeliste', 1);


-- Test til wish tabel
INSERT INTO wish (
    id, list_id, name, description, price, link, image, priority 
)
VALUES (
    1,
    1, 
    'iPhone', 
    'Det skal være en iPhone 15, helst i sort, og IKKE en iphone15 pro!', 
    3129,
    'https://greenmind.dk/telefoner/iphones/iphone-15/iphone-15/apple-iphone-15-128-gb-dual-sim-nano-esim-sort-okay-118909', 
    'https://greenmind.dk/web/image/product.product/118909/image_1920/350x430/6ae0fbda_iphone-15_350x430',
    TRUE
),
(
    2,
    2, 
    'AirPods Pro 2', 
    'Airpods pro, husk det skal være mindst 2nd generation', 
    1199,
    'https://www.refurbed.dk/p/apple-airpods-pro-2/', 
    'https://files.refurbed.com/ii/apple-airpods-pro-2-1699965335.jpg?t=fitdesign&h=600&w=800&t=convert&f=webp',
    FALSE
),
(
    3,
    1, 
    'GoKart oplevelse', 
    'Et gavekort til GoKart, helst fra RaceHall København', 
    400,
    'https://racehall.com/gavekort/', 
    NULL,
    FALSE
),
(
    4,
    1,
    'Nintendo Switch 2',
    'Nintendo Switch 2. Det skal være den nye Switch 2 og IKKE den originale Nintendo Switch.',
    4199,
    'https://www.elgiganten.dk/product/gaming/spillekonsol-tilbehor/nintendo-switch/nintendo-spillekonsol/nintendo-switch-2-spilkonsol/913960',
    NULL,
    TRUE
),
(
    5,
    2,
    'LEGO Star Wars',
    'Et LEGO Star Wars-sæt. Helst et større sæt med et rumskib, fx Millennium Falcon.',
    1200,
    'https://www.lego.com/da-dk/themes/star-wars',
    NULL,
    FALSE
),
(
    6,
    3,
    'Gavekort til biografen',
    'Et gavekort til en biograftur med popcorn og sodavand. Gerne til Nordisk Film Biografer.',
    300,
    'https://www.nfbio.dk/',
    NULL,
    FALSE
);

