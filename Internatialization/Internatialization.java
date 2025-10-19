import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Internatialization{

    public static void main(String[] args){

        
           // Устанавливаем локаль (язык и страна)
        Locale russian = Locale.forLanguageTag("ru-RU");
        Locale english = Locale.ENGLISH;
        //new Locale("en");
        Locale german = new Locale("de"); 
        
        // Тестируем разные локали
        displayMessages(english);
        displayMessages(russian);
        displayMessages(german); 
    }

    public static void displayMessages(Locale locale) {
        
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
        
        System.out.println("\n=== " + locale.getDisplayName() + " ===");
        System.out.println(messages.getString("greeting"));
        System.out.println(messages.getString("farewell"));
        
        // Сообщение с параметрами
        String welcomeMessage = messages.getString("welcome");
        String formattedWelcome = MessageFormat.format(welcomeMessage, "Иван", 5);
        System.out.println(formattedWelcome);
    }
}