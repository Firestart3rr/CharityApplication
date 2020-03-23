package pl.coderslab.charity.registration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import pl.coderslab.charity.entity.AppUser;

import java.util.Locale;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appURL;
    private Locale locale;
    private AppUser appUser;

    public OnRegistrationCompleteEvent(String appURL, Locale locale, AppUser appUser) {
        super(appUser);
        this.appURL = appURL;
        this.locale = locale;
        this.appUser = appUser;
    }
}
