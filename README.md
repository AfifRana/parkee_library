# Getting Started

### Starting The Application
1. Prepare the database
2. Input user and password in `application.properties`
3. Run the following command to start the application:
    ```shell
    mvn spring-boot:run
    ```
4. Open the browser and go to `http://localhost:8080/swagger-ui.html` to test the API

### DDL

```sql
CREATE TABLE books (
	id bigserial NOT NULL,
	title varchar(255) NOT NULL,
	isbn varchar(255) NOT NULL,
	stock int4 NOT NULL,
	CONSTRAINT books_isbn_key UNIQUE (isbn),
	CONSTRAINT books_pkey PRIMARY KEY (id),
	CONSTRAINT books_stock_check CHECK ((stock >= 0))
);

CREATE TABLE public.borrowers (
	id bigserial NOT NULL,
	ktp_number varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	CONSTRAINT borrowers_email_key UNIQUE (email),
	CONSTRAINT borrowers_ktp_number_key UNIQUE (ktp_number),
	CONSTRAINT borrowers_pkey PRIMARY KEY (id)
);

CREATE TABLE public.borrowings (
	id bigserial NOT NULL,
	borrower_id int8 NOT NULL,
	book_id int8 NOT NULL,
	borrow_date date DEFAULT CURRENT_TIMESTAMP NULL,
	return_deadline date NOT NULL,
	return_date date NULL,
	status varchar(255) NULL,
	CONSTRAINT borrowings_pkey PRIMARY KEY (id),
	CONSTRAINT borrowings_status_check CHECK (((status)::text = ANY ((ARRAY['BORROWED'::character varying, 'RETURNED'::character varying, 'LATE'::character varying])::text[]))),
	CONSTRAINT chk_borrow_duration CHECK ((return_deadline <= (borrow_date + '30 days'::interval)))
);
```