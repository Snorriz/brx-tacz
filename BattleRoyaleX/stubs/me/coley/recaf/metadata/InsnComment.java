package me.coley.recaf.metadata;
import java.lang.annotation.*;
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR})
public @interface InsnComment {
   String value() default "";
   String At_1() default "";
   String At_2() default "";
   String At_3() default "";
}
