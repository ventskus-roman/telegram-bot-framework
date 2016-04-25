# telegram-bot-framework
Simple framework for creation bots for Telegram on Java

You need just create entry point. For example:

```java
public class Application {

    public static void main(String[] args) {
        Framework.init(new FrameworkParams("BOT_NAME", "BOT_TOKEN"));
    }
}
```

Then you need to create Controller for each of your bot command. For example, this is Controller for /test command

```java
@BotController(value = "/last")
public class SimpleController implements Controller {

    private Parameter firstName = new Parameter("firstName");
    private Parameter secondName = new Parameter("secondName");

    @Override
    public Response process(Request request, Map<Parameter, String> params) {
        if (params.get(firstName) == null) {
            return new AppealInParameterResponse("What is your first name?", firstName, request.getCommand(), request.getUser());
        } else {
            return new SingleMessageResponse("Hello, " + params.get(firstName) + "!", request.getCommand(), request.getUser());
        }
    }
}
```

Controller must be annotated with @BotController and implements Controller interface

Also you can use awesome ReplyKeyboardMarkupBuilder, that make possible easy creation of ReplyKeyboarMarkup (see https://core.telegram.org/bots/api#replykeyboardmarkup)

Example of usege:
```java
ReplyKeyboardMarkup markup = new ReplyKeyboardMarkupBuilder().addRow("Today", "Tomorrow").oneTimeKeyboad().build();
Response response = new SimpleManyMessageResponse(buildMessages(), request.getCommand(),request.getUser(), markup);
```
This code will build for you menu with two options "Today" and "Tomorrow"

Based on https://github.com/rubenlagus/TelegramBots
