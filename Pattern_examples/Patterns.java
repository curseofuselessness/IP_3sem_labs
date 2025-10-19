public class Patterns{


    public static class Logger { // Singleton
    
        private static Logger ref;
    
   
        private Logger() {
            System.out.println("Logger created");
        }
    
        public static Logger Get() {
            if (ref == null) {
                ref = new Logger();
            }
            return ref;
        }
    
        public void log(String message) {
            System.out.println("LOG: " + message);
        }
    }
    
    static class Person { // Creator
    private String name;
    private int age;
    
    
    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public static class Builder {
        private String name;
        private int age;
        
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder setAge(int age) {
            this.age = age;
            return this;
        }
        
        public Person build() {
            return new Person(name, age);
        }
    }
    
    @Override
    public String toString() {
        return name + ", " + age + " years";
    }
}

// Decorator
interface Coffee {
    String make();
}

class SimpleCoffee implements Coffee {
    public String make() {
        return "coffe";
    }
}

class MilkDecorator implements Coffee {
    private Coffee coffee;
    
    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    
    public String make() {
        return coffee.make() + " with milk";
    }
}

class SugarDecorator implements Coffee {
    private Coffee coffee;
    
    public SugarDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    
    public String make() {
        return coffee.make() + " with sugar";
    }
}


    public static void main(String[] args){
        Logger logger1 = Logger.Get();

        logger1.log("logger test");

         Person person = new Person.Builder()
            .setName("Jack")
            .setAge(25)
            .build();
            
        System.out.println(person);


    }
}