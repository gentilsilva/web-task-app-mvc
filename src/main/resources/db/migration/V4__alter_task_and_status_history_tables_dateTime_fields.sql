ALTER TABLE tasks
    ALTER COLUMN created TYPE TIMESTAMP(0) WITHOUT TIME ZONE,
    ALTER COLUMN finished TYPE TIMESTAMP(0) WITHOUT TIME ZONE;

ALTER TABLE status_history
    ALTER COLUMN updated TYPE TIMESTAMP(0) WITHOUT TIME ZONE;