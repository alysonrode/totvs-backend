CREATE TABLE bill (
    id uuid NOT NULL,
    description character varying(255),
    due_date timestamp(6),
    payday timestamp(6),
    value numeric(38,2),
    situation smallint,
    CONSTRAINT "bill_pkey" PRIMARY KEY ("id")
);