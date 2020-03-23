package pl.coderslab.charity.registration.listener;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.registration.OnRegistrationCompleteEvent;
import pl.coderslab.charity.service.EmailServiceImpl;
import pl.coderslab.charity.service.VerificationTokenService;

import java.util.UUID;

@AllArgsConstructor
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final MessageSource messages;
    private final EmailServiceImpl emailService;
    private final VerificationTokenService verificationTokenService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        AppUser appUser = event.getAppUser();
        String token = UUID.randomUUID().toString();
        verificationTokenService.createVerificationToken(appUser, token);

        String recipientAddress = appUser.getEmail();
        String subject = "Potwierdź rejestrację";
        String confirmationUrl
                = event.getAppURL() + "/register/registrationConfirm?token=" + token;
        String text = "Link do weryfikacji: \n " + "http://localhost:8082" + confirmationUrl;

        emailService.sendSimpleMessage(recipientAddress, subject, text);
    }
}

