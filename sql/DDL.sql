create table diary(
    id INT not null primary key auto_increment,
    weather VARCHAR(50) NOT NULL,
    icon VARCHAR(50) NOT NULL,
    temperature DOUBLE NOT NULL,
    text VARCHAR(500) NOT NULL,
    date DATE NOT NULL
);

create table date_weather(
    date DATE not null primary key,
    weather VARCHAR(50) NOT NULL,
    icon VARCHAR(50) NOT NULL,
    temperature DOUBLE NOT NULL
);