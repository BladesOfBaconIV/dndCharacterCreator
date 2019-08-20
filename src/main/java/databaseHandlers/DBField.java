package databaseHandlers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/*
 Annotation used to mark fields in a class that should be stored in a table
 Usage;
    1) @DBField("field name") // For storing a value in a single field
    2) @DBField(values = {"field1, field2, ..."}) // For storing an array of values in different fields*
    3) @DBField(DBField.GET_DETAILS) // For if you want to store the fields of some object instead of the object itself
    *N.B: The length of the array must be the same length as the number of field names given
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DBField {

    public static final String GET_DETAILS = "GET DETAILS";

    public String value() default "";
    public String[] values() default {};

}
