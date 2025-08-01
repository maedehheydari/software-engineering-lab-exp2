import edu.sharif.selab.models.EmailMessage;
import edu.sharif.selab.models.Message;
import edu.sharif.selab.models.SmsMessage;
import edu.sharif.selab.models.TelegramMessage;
import edu.sharif.selab.services.IEmailMessageService;
import edu.sharif.selab.services.ISmsMessageService;
import edu.sharif.selab.services.ITelegramMessageService;
import edu.sharif.selab.services.MessageServiceFactory;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello and Welcome to SE Lab Messenger.");
        int userAnswer = 0;
        do {
            Message message = null;
            String source;
            String target;
            String content;

            System.out.println("In order to send Sms message enter 1");
            System.out.println("In order to send Email message enter 2");
            System.out.println("In order to send Telegram message enter 3");
            System.out.println("In order to Exit, Enter 0");

            userAnswer = scanner.nextInt();

            if (userAnswer == 0) break;

            switch (userAnswer) {
                case 1:
                    SmsMessage sms = new SmsMessage();
                    fillFields("phone", sms::setSourcePhoneNumber, sms::setTargetPhoneNumber, sms::setContent, true);
                    message = sms;
                    break;
                case 2:
                    EmailMessage email = new EmailMessage();
                    fillFields("email", email::setSourceEmailAddress, email::setTargetEmailAddress, email::setContent, false);
                    message = email;
                    break;
                case 3:
                    TelegramMessage telegram = new TelegramMessage();
                    fillFields("Telegram ID", telegram::setSourceId, telegram::setTargetId, telegram::setContent, false);
                    message = telegram;
                    break;
            }

            if (message instanceof SmsMessage) {
                ISmsMessageService smsService = MessageServiceFactory.createSmsService();
                smsService.sendSmsMessage((SmsMessage) message);
            } else if (message instanceof EmailMessage) {
                IEmailMessageService emailService = MessageServiceFactory.createEmailService();
                emailService.sendEmailMessage((EmailMessage) message);
            } else if (message instanceof TelegramMessage) {
                ITelegramMessageService telegramService = MessageServiceFactory.createTelegramService();
                telegramService.sendTelegramMessage((TelegramMessage) message);
            }

        } while (true);
    }

    private static void fillFields(
        String label,
        Consumer<String> setSource,
        Consumer<String> setTarget,
        Consumer<String> setContent,
        boolean useMultilineScanner
    ) {
        System.out.print("Enter source " + label + " : ");
        setSource.accept(scanner.next());
        System.out.print("Enter target " + label + " : ");
        setTarget.accept(scanner.next());
        System.out.println("Write Your Message : ");
        if (useMultilineScanner) {
            setContent.accept(scanner.next(".*$"));
        } else {
            setContent.accept(scanner.next());
        }
    }
}
