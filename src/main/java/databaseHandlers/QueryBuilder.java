package databaseHandlers;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class QueryBuilder {

    private HashMap<String, String> fieldValues;
    private String keyColumn;
    private String table;

    /*
     Used to create SQL queries for an object

     @attribute fieldValues: Private, HashMap of <Field, Value> for the object used to create this
     @attribute keyColumn: Private, name of the primary key column in the SQLite table
     @attribute table: Private, name of the table in the SQLite database
     */
    public QueryBuilder(Object object, String keyColumn, String table) throws IllegalAccessException {
        this.fieldValues = getFieldsValues(object);
        this.keyColumn = keyColumn;
        this.table = table;
    }

    /*
     Creates the insert statement for the Object used to make this QueryBuilder

     @return String: The insert statement
     */
    public String insertStatement() {
        String[] fieldValueStrings = this.getFieldValueStrings();
        return String.format(
                "INSERT INTO %s(%s) VALUES(%s);",
                this.table,
                fieldValueStrings[0],
                fieldValueStrings[1]
        );
    }

    /*
     Creates the delete Statement for the Object used to make this QueryBuilder

     @return String: The delete statement to delete this object
     */
    public String deleteStatement() {
        return String.format(
                "DELETE FROM %s WHERE %s='%s';",
                this.table,
                this.keyColumn,
                this.fieldValues.get(this.keyColumn)
        );
    }

    /*
     Creates the statement used to select all objects from a table

     @param table: Table to select the objects from
     */
    public static String selectAll(String table) {
        return String.format("SELECT * FROM %s;", table);
    }

    /*
     Produces a HashMap of <FieldName, Value> pairs for the given objects
     Uses the @DBField annotations to get the fields to be saved and field names

     @Param object: The object to make the HashMap for
     @return HashMap<String, String>: HashMap of <Field, Value> pairs
     */
    private HashMap<String, String> getFieldsValues(Object object) throws IllegalAccessException {
        HashMap fieldsValues = new HashMap<String, String>();
        for (Field field :  object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(DBField.class)) {
                String fieldName = field.getAnnotation(DBField.class).value();
                Object value = field.get(object);
                // If the field is marked GET_DETAILS get the <Field, Value> pairs of value recursively
                if (fieldName.equals(DBField.GET_DETAILS)) {
                    fieldsValues.putAll(getFieldsValues(value));
                    continue;
                }
                // If the value is an array
                if (value.getClass().isArray()) {
                    String[] fieldNames = field.getAnnotation(DBField.class).values();
                    // If each value in value array should have its own field in the DB
                    if ( fieldNames.length != 0 && value.getClass().isArray()
                            && fieldNames.length == Array.getLength(value)) {
                        for (int i = 0; i < fieldNames.length; i++) {
                            fieldsValues.put(fieldNames[i], Array.get(value, i).toString());
                        }
                    } else {
                        // TODO Decide how (or if should be able) to store arrays in one field
                        // eg. Race.languages
                    }
                } else { // Just a single value to store in a single field
                    fieldsValues.put(fieldName, value.toString());
                }
            }
        }
        return fieldsValues;
    }

    /*
     Returns an array of length 2 {fields, values} in comma separated lists

     @return String[]: length 2 {"field1, field2, ...", "value1, value2, ..."}
     */
    private String[] getFieldValueStrings() {
        // Puts the fields/values in ArrayLists to make sure they are in the correct orders
        ArrayList<String> fields = new ArrayList<String>();
        ArrayList<String> values = new ArrayList<String>();
        for (String key : this.fieldValues.keySet()) {
            fields.add(key);
            values.add("'" + this.fieldValues.get(key) + "'");
        }
        return new String[] {
                fields.stream().collect(Collectors.joining(", ")),
                values.stream().collect(Collectors.joining(", "))
        };
    }

}
