// 文件名：ReflectionDemo.java
public class ReflectionDemo {
    public static void main(String[] args) throws Exception {
        // 1) 通过类名加载 Class 对象
        Class<?> clazz = Class.forName("Person");

        // 2) 通过反射创建对象（无参构造 + newInstance，适配 Java 8 及更早）
        Object person = clazz.newInstance();

        // 3) 访问/修改 public 字段：getField + get/set
        java.lang.reflect.Field nameField = clazz.getField("name"); // public 字段
        java.lang.reflect.Field ageField  = clazz.getField("age");  // public 字段

        System.out.println("初始 name = " + nameField.get(person)); // 可能为 null（默认值）
        System.out.println("初始 age  = " + ageField.get(person));  // 0

        nameField.set(person, "Alice"); // Field#set
        ageField.set(person, 25);       // Field#set

        System.out.println("修改后 name = " + nameField.get(person)); // Field#get
        System.out.println("修改后 age  = " + ageField.get(person));

        // 4) 调用方法：getDeclaredMethod + invoke
        // 4.1 public 方法
        java.lang.reflect.Method helloMethod = clazz.getDeclaredMethod("hello", String.class);
        Object helloRet = helloMethod.invoke(person, "Hi");
        System.out.println("hello 返回: " + helloRet);

        // 4.2 private 方法（需 setAccessible(true)）
        java.lang.reflect.Method secretMethod = clazz.getDeclaredMethod("secret", int.class);
        secretMethod.setAccessible(true);
        Object secretRet = secretMethod.invoke(person, 5);
        System.out.println("secret 返回: " + secretRet);

        // 5) 访问/修改 private 字段（可选示例）
        java.lang.reflect.Field salaryField = clazz.getDeclaredField("salary");
        salaryField.setAccessible(true);
        System.out.println("原 salary = " + salaryField.get(person));
        salaryField.set(person, 8888.88);
        System.out.println("修改后 salary = " + salaryField.get(person));
    }
}

// 目标类（无包名，便于单文件演示；实际项目建议使用包名）
class Person {
    public String name;
    public int age;
    private double salary;

    // 无参构造：便于在 Java 8/更早使用 Class#newInstance()
    public Person() {
        this.name = null;   // 默认 null
        this.age = 0;       // 默认 0
        this.salary = 1234.56;
    }

    public String hello(String prefix) {
        return prefix + ", I'm " + name + ", " + age + " years old.";
    }

    private String secret(int bonus) {
        return "salary + bonus = " + (salary + bonus);
    }
}